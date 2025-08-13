package com.example.service.controller;

import com.example.service.entity.Instructor;
import com.example.service.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
@RequiredArgsConstructor
public class InstructorController {
    private static final Logger logger = LoggerFactory.getLogger(InstructorController.class);
    private final InstructorService instructorService;

    @GetMapping
    public List<Instructor> getAllInstructors() {
        logger.info("Fetching all instructors");
        return instructorService.getAllInstructors();
    }

    @GetMapping("/{id}")
    public Instructor getInstructorById(@PathVariable Long id) {
        logger.info("Fetching instructor by id: {}", id);
        return instructorService.getInstructorById(id);
    }

    @PostMapping
    public Instructor createInstructor(@RequestBody Instructor instructor) {
        logger.info("Creating new instructor");
        return instructorService.saveInstructor(instructor);
    }

    @PutMapping("/{id}")
    public Instructor updateInstructor(@PathVariable Long id, @RequestBody Instructor instructor) {
        logger.info("Updating instructor with id: {}", id);
        return instructorService.updateInstructor(id, instructor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable Long id) {
        logger.info("Deleting instructor with id: {}", id);
        instructorService.deleteInstructor(id);
        return ResponseEntity.noContent().build();
    }
}
