package com.example.service.controller;

import com.example.service.entity.Student;
import com.example.service.service.StudentService;
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

class StudentControllerTest {
    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    private Student student;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
        student = new Student();
        student.setId(1L);
        student.setName("John Doe");
        student.setEmail("john.doe@example.com");
    }

    @Test
    void testGetAllStudents() throws Exception {
        List<Student> students = Arrays.asList(student);
        when(studentService.getAllStudents()).thenReturn(students);
        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetStudentById() throws Exception {
        when(studentService.getStudentById(1L)).thenReturn(student);
        mockMvc.perform(get("/api/students/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateStudent() throws Exception {
        when(studentService.saveStudent(any(Student.class))).thenReturn(student);
        mockMvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"John Doe\",\"email\":\"john.doe@example.com\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateStudent() throws Exception {
        when(studentService.updateStudent(eq(1L), any(Student.class))).thenReturn(student);
        mockMvc.perform(put("/api/students/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"John Doe\",\"email\":\"john.doe@example.com\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteStudent() throws Exception {
        doNothing().when(studentService).deleteStudent(1L);
        mockMvc.perform(delete("/api/students/1"))
                .andExpect(status().isNoContent());
    }
}

