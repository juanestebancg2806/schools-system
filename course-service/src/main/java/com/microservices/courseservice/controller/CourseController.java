package com.microservices.courseservice.controller;

import com.microservices.courseservice.model.Course;
import com.microservices.courseservice.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/courses")
@CrossOrigin("*")
public class CourseController {

    private CourseService courseService;

    public  CourseController(CourseService courseService){
        this.courseService = courseService;
    }


    @GetMapping
    public ResponseEntity<?> getCourses(@RequestParam Optional<String> ids){
        if(ids.isPresent() && ids.get().length() > 0){
            List<Long> idsList = Stream.of(ids.get().split(","))
                    .map(String::trim)
                    .map(Long::parseLong)
                    .collect(Collectors.toList());;
            return  new ResponseEntity<>(this.courseService.getCoursesByIds(idsList), HttpStatus.OK);
        }

        return new ResponseEntity<>(this.courseService.getCourses(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody Course course){
        try{
            Course createdCourse = this.courseService.addCourse(course);
            return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Some attributes missing",HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable long id){
        Course course = this.courseService.getCourseById(id);
        if(course != null){
            return new ResponseEntity<>(course, HttpStatus.OK);
        }
       else{
           return new ResponseEntity<>("Course not found",HttpStatus.NOT_FOUND);
        }
    }




}
