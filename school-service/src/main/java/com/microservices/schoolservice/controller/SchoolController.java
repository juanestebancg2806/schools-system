package com.microservices.schoolservice.controller;

import com.microservices.schoolservice.model.School;
import com.microservices.schoolservice.service.SchoolService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/schools")
@RestController
public class SchoolController {
    private SchoolService schoolService;

    public SchoolController(SchoolService schoolService){
        this.schoolService = schoolService;
    }

    @PostMapping
    public ResponseEntity<?> addSchool(@RequestBody School school){
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(this.schoolService.addSchool(school), headers, HttpStatus.CREATED);
        }catch (TransactionSystemException e){
            System.out.println(e);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<School> getSchools(){
        return this.schoolService.getSchools();
    }

    @GetMapping("/{id}")
    public School getSchoolById(@PathVariable int id){
        return this.schoolService.getSchoolById(id);
    }
}
