package com.microservices.studentservice.service;

import com.microservices.studentservice.dto.Course;
import com.microservices.studentservice.dto.School;
import com.microservices.studentservice.dto.StudentResponse;
import com.microservices.studentservice.model.Student;
import com.microservices.studentservice.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    private RestTemplate restTemplate;

    public StudentService(StudentRepository studentRepository, RestTemplate restTemplate){
        this.studentRepository = studentRepository;
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<?> createStudent(Student student){
        try{
            return new ResponseEntity<Student>(this.studentRepository.save(student), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<?> getStudentById( String id){
        Optional<Student> student = this.studentRepository.findById(id);
        if(student.isPresent()){
            School school = restTemplate.getForObject
                    ("http://SCHOOL-SERVICE/schools/" + student.get().getSchoolId(), School.class);

            List<Course> courses = restTemplate.getForObject(
                    "http://ENROLLMENT-SERVICE/enrollments/students/"+student.get().getId(),
                    ArrayList.class);

            StudentResponse studentResponse = new StudentResponse(
                    student.get().getId(),
                    student.get().getName(),
                    student.get().getAge(),
                    student.get().getGender(),
                    school,
                    courses
            );
            return new ResponseEntity<>(studentResponse, HttpStatus.OK);

        }
        else{
            return new ResponseEntity<>("No student found",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> getStudents(){
        List<Student> students = this.studentRepository.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }


}
