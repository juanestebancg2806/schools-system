package com.microservices.schoolservice.service;


import com.microservices.schoolservice.model.School;
import com.microservices.schoolservice.repository.SchoolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {
    private SchoolRepository schoolRepository;

    public SchoolService(SchoolRepository schoolRepository){
        this.schoolRepository = schoolRepository;
    }

    public School addSchool(School school){
        return this.schoolRepository.save(school);
    }

    public List<School> getSchools(){
        return this.schoolRepository.findAll();
    }

    public School getSchoolById(int id){
        return this.schoolRepository.findById(id).orElse(null);
    }


}
