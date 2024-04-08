package com.microservices.courseservice.service;


import com.microservices.courseservice.model.Course;
import com.microservices.courseservice.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    public Course addCourse(Course course){
        return this.courseRepository.save(course);
    }

    public List<Course> getCourses(){
        return this.courseRepository.findAll();
    }

    public Course getCourseById(long id){
        return  this.courseRepository.findById(id).orElse(null);
    }

    public List<Course> getCoursesByIds(List<Long> ids){
        return this.courseRepository.findByIdIn(ids);
    }
}
