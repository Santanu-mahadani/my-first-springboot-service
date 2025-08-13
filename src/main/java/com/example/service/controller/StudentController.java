package com.example.service.controller;

import com.example.service.entity.Student;
import com.example.service.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    private final StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        logger.info("Fetching all students");
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        logger.info("Fetching student by id: {}", id);
        return studentService.getStudentById(id);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        logger.info("Creating new student");
        return studentService.saveStudent(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        logger.info("Updating student with id: {}", id);
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        logger.info("Deleting student with id: {}", id);
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}

