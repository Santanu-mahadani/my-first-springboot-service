package com.example.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "Student Course Management API",
        version = "1.0",
        description = "API for managing students, courses, departments, instructors and enrollments"
    )
)
public class StudentCourseApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentCourseApplication.class, args);
    }
}
