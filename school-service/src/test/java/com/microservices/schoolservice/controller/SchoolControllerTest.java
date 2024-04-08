package com.microservices.schoolservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.schoolservice.model.School;
import com.microservices.schoolservice.service.SchoolService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*
-Using WebMvcTest annotation will disable full auto-configuration
and instead apply only configuration relevant to MVC tests (i.e. @Controller, @ControllerAdvice,
 @JsonComponent, Converter/GenericConverter, Filter, WebMvcConfigurer and HandlerMethodArgumentResolver beans but not @Component, @Service or @Repository beans) — source: Java doc

-SpringExtension integrates the Spring TestContext Framework into JUnit 5’s Jupiter programming model.

-MockMVC class is part of Spring MVC test framework which helps in testing the controllers explicitly
starting a Servlet container.

-MockBean is used to add mock objects to the Spring application context. This way to can create dummies
and perform operations. We need to inject a mock of the Service here to perform a mocking behavior.
It will be discussed late

https://www.springcloud.io/post/2022-09/spring-boot-micro-service-test/#gsc.tab=0
https://medium.com/javarevisited/restful-api-testing-in-java-with-mockito-controller-layer-f4605f8ffaf3

 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(SchoolController.class)
class SchoolControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SchoolService schoolService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getSchoolsTest() throws  Exception{
        School school = new School(1,"school1","cali","juan");
        when(this.schoolService.getSchools()).thenReturn(List.of(school));
        mockMvc.perform(MockMvcRequestBuilders.get("/schools"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void addSchoolTest() throws Exception{
        School school = new School(1,"school1","cali","juan");

        given(this.schoolService
                .addSchool(ArgumentMatchers.any()))
                .willAnswer(invocation -> invocation.getArgument(0));


        when(this.schoolService.addSchool(school))
                .thenReturn(school);
        mockMvc
                .perform(MockMvcRequestBuilders.post("/schools")
                        .content(this.objectMapper.writeValueAsString(school))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.id", CoreMatchers.is(school.getId())));

    }

}