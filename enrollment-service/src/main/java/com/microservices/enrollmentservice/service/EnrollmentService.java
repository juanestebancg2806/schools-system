package com.microservices.enrollmentservice.service;

import com.microservices.enrollmentservice.dto.Course;
import com.microservices.enrollmentservice.model.Enrollment;
import com.microservices.enrollmentservice.repository.EnrollmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class EnrollmentService {

    private EnrollmentRepository enrollmentRepository;


    public EnrollmentService(EnrollmentRepository enrollmentRepository){
        this.enrollmentRepository = enrollmentRepository;
    }

    public Enrollment addEnrollment(Enrollment enrollment){
        return  this.enrollmentRepository.save(enrollment);
    }

    public List<Enrollment> getEnrollments(){
        return this.enrollmentRepository.findAll();
    }

    public Enrollment getEnrollmentById(long id){
        return this.enrollmentRepository.findById(id).orElse(null);
    }

    public List<Enrollment> getEnrollmentsByStudentId(String id){
          return this.enrollmentRepository.findByStudentId(id);
    }
}
