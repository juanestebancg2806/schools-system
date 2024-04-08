package com.microservices.studentservice.controller;


import com.microservices.studentservice.model.Student;
import com.microservices.studentservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable String id){
        return studentService.getStudentById(id);
    }
    @GetMapping
    public ResponseEntity<?> getStudents(){
        return studentService.getStudents();
    }
    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }

}