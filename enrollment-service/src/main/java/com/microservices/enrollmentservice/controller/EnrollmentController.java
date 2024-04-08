package com.microservices.enrollmentservice.controller;


import com.microservices.enrollmentservice.dto.Course;
import com.microservices.enrollmentservice.dto.Student;
import com.microservices.enrollmentservice.model.Enrollment;
import com.microservices.enrollmentservice.service.EnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/enrollments")
@CrossOrigin("*")
public class EnrollmentController {

    private EnrollmentService enrollmentService;

    private RestTemplate restTemplate;
    public EnrollmentController(EnrollmentService enrollmentService, RestTemplate restTemplate){
        this.enrollmentService = enrollmentService;
        this.restTemplate = restTemplate;
    }

    @PostMapping
    public ResponseEntity<?> enrollStudent(@RequestBody Enrollment enrollment){
        if(isValidStudent(enrollment.getStudentId()) && isValidCourse(enrollment.getCourseId())){
            return new ResponseEntity<>(this.enrollmentService.addEnrollment(enrollment), HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("Bad information", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public List<Enrollment> getEnrollments(){
        return this.enrollmentService.getEnrollments();
    }

    public boolean isValidStudent(String id){
        return restTemplate.
                getForEntity("http://STUDENT-SERVICE/students/"+id, Student.class).getStatusCode()
                == HttpStatus.OK;

    }

    public boolean isValidCourse(long id){
        return restTemplate.
                getForEntity("http://COURSE-SERVICE/courses/"+id, Course.class).getStatusCode()
                == HttpStatus.OK;

    }

    @GetMapping("/students/{studentId}")
    public List<Course> getStudentCourses(@PathVariable String studentId){
        List<Enrollment> enrollments =  this.enrollmentService.getEnrollmentsByStudentId(studentId);
        Map<String, String> map = new HashMap<>();
        StringBuilder ids = new StringBuilder();
        int i;
        for(i = 0; i < enrollments.size();i++){
            ids.append(enrollments.get(i).getCourseId());
            if(i+1 < enrollments.size()){
                ids.append(",");
            }
        }
        map.put("ids",ids.toString());
        List<Course> courses = this.restTemplate.getForObject
                ("http://COURSE-SERVICE/courses?ids={ids}", ArrayList.class,map);

        return courses;


    }


}
