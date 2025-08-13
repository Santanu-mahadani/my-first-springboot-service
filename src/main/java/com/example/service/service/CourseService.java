package com.example.service.service;

import com.example.service.entity.Course;
import java.util.List;

public interface CourseService {
    Course saveCourse(Course course);
    Course getCourseById(Long id);
    List<Course> getAllCourses();
    Course updateCourse(Long id, Course course);
    void deleteCourse(Long id);
}

