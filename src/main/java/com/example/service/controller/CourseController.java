package com.example.service.controller;

import com.example.service.entity.Course;
import com.example.service.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
    private final CourseService courseService;

    @GetMapping
    public List<Course> getAllCourses() {
        logger.info("Fetching all courses");
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id) {
        logger.info("Fetching course by id: {}", id);
        return courseService.getCourseById(id);
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        logger.info("Creating new course");
        return courseService.saveCourse(course);
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course course) {
        logger.info("Updating course with id: {}", id);
        return courseService.updateCourse(id, course);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        logger.info("Deleting course with id: {}", id);
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}

