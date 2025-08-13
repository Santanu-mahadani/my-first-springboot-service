package com.example.service.service;

import com.example.service.entity.Course;
import com.example.service.exception.ResourceNotFoundException;
import com.example.service.repository.CourseRepository;
import com.example.service.service.impl.CourseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseServiceImplTest {
    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseServiceImpl courseService;

    private Course course;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        course = new Course();
        course.setId(1L);
        course.setName("Algorithms");
        course.setCode("CS101");
    }

    @Test
    void testSaveCourse() {
        when(courseRepository.save(course)).thenReturn(course);
        Course saved = courseService.saveCourse(course);
        assertEquals("Algorithms", saved.getName());
    }

    @Test
    void testGetCourseById_Found() {
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        Course found = courseService.getCourseById(1L);
        assertEquals("Algorithms", found.getName());
    }

    @Test
    void testGetCourseById_NotFound() {
        when(courseRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> courseService.getCourseById(2L));
    }

    @Test
    void testGetAllCourses() {
        when(courseRepository.findAll()).thenReturn(Arrays.asList(course));
        List<Course> courses = courseService.getAllCourses();
        assertEquals(1, courses.size());
    }

    @Test
    void testUpdateCourse() {
        Course updated = new Course();
        updated.setName("Advanced Algorithms");
        updated.setCode("CS201");
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(courseRepository.save(any(Course.class))).thenReturn(updated);
        Course result = courseService.updateCourse(1L, updated);
        assertEquals("Advanced Algorithms", result.getName());
    }

    @Test
    void testDeleteCourse() {
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        doNothing().when(courseRepository).delete(course);
        assertDoesNotThrow(() -> courseService.deleteCourse(1L));
    }
}

