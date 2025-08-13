package com.example.service.service.impl;

import com.example.service.entity.Course;
import com.example.service.exception.ResourceNotFoundException;
import com.example.service.repository.CourseRepository;
import com.example.service.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
    private final CourseRepository courseRepository;

    @Override
    public Course saveCourse(Course course) {
        logger.info("Saving course: {}", course.getName());
        return courseRepository.save(course);
    }

    @Override
    public Course getCourseById(Long id) {
        logger.info("Fetching course by id: {}", id);
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
    }

    @Override
    public List<Course> getAllCourses() {
        logger.info("Fetching all courses");
        return courseRepository.findAll();
    }

    @Override
    public Course updateCourse(Long id, Course courseDetails) {
        Course course = getCourseById(id);
        course.setName(courseDetails.getName());
        course.setCode(courseDetails.getCode());
        course.setCredits(courseDetails.getCredits());
        course.setDescription(courseDetails.getDescription());
        course.setInstructor(courseDetails.getInstructor());
        logger.info("Updating course: {}", id);
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        Course course = getCourseById(id);
        logger.info("Deleting course: {}", id);
        courseRepository.delete(course);
    }
}

