package com.microservices.enrollmentservice.repository;

import com.microservices.enrollmentservice.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment,Long> {
    List<Enrollment> findByStudentId(String studentId);
    List<Enrollment> findByCourseId(Long courseId);


}
