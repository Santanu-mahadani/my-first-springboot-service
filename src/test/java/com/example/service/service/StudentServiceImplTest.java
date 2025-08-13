package com.example.service.service;

import com.example.service.entity.Student;
import com.example.service.exception.ResourceNotFoundException;
import com.example.service.repository.StudentRepository;
import com.example.service.service.impl.StudentServiceImpl;
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

class StudentServiceImplTest {
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    private Student student;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        student = new Student();
        student.setId(1L);
        student.setName("John Doe");
        student.setEmail("john.doe@example.com");
    }

    @Test
    void testSaveStudent() {
        when(studentRepository.save(student)).thenReturn(student);
        Student saved = studentService.saveStudent(student);
        assertEquals("John Doe", saved.getName());
    }

    @Test
    void testGetStudentById_Found() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        Student found = studentService.getStudentById(1L);
        assertEquals("John Doe", found.getName());
    }

    @Test
    void testGetStudentById_NotFound() {
        when(studentRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> studentService.getStudentById(2L));
    }

    @Test
    void testGetAllStudents() {
        when(studentRepository.findAll()).thenReturn(Arrays.asList(student));
        List<Student> students = studentService.getAllStudents();
        assertEquals(1, students.size());
    }

    @Test
    void testUpdateStudent() {
        Student updated = new Student();
        updated.setName("Jane Doe");
        updated.setEmail("jane.doe@example.com");
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentRepository.save(any(Student.class))).thenReturn(updated);
        Student result = studentService.updateStudent(1L, updated);
        assertEquals("Jane Doe", result.getName());
    }

    @Test
    void testDeleteStudent() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        doNothing().when(studentRepository).delete(student);
        assertDoesNotThrow(() -> studentService.deleteStudent(1L));
    }
}

