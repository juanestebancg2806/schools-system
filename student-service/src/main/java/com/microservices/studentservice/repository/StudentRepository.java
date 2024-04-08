package com.microservices.studentservice.repository;

import com.microservices.studentservice.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {

}
