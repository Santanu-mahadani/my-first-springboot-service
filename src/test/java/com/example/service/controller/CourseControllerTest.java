package com.example.service.controller;

import com.example.service.entity.Course;
import com.example.service.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CourseControllerTest {
    private MockMvc mockMvc;

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController courseController;

    private Course course;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(courseController).build();
        course = new Course();
        course.setId(1L);
        course.setName("Algorithms");
        course.setCode("CS101");
    }

    @Test
    void testGetAllCourses() throws Exception {
        List<Course> courses = Arrays.asList(course);
        when(courseService.getAllCourses()).thenReturn(courses);
        mockMvc.perform(get("/api/courses"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetCourseById() throws Exception {
        when(courseService.getCourseById(1L)).thenReturn(course);
        mockMvc.perform(get("/api/courses/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateCourse() throws Exception {
        when(courseService.saveCourse(any(Course.class))).thenReturn(course);
        mockMvc.perform(post("/api/courses")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Algorithms\",\"code\":\"CS101\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateCourse() throws Exception {
        when(courseService.updateCourse(eq(1L), any(Course.class))).thenReturn(course);
        mockMvc.perform(put("/api/courses/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Algorithms\",\"code\":\"CS101\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteCourse() throws Exception {
        doNothing().when(courseService).deleteCourse(1L);
        mockMvc.perform(delete("/api/courses/1"))
                .andExpect(status().isNoContent());
    }
}

