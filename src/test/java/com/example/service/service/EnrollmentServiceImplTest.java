package com.example.service.service;

import com.example.service.entity.Enrollment;
import com.example.service.exception.ResourceNotFoundException;
import com.example.service.repository.EnrollmentRepository;
import com.example.service.service.impl.EnrollmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EnrollmentServiceImplTest {
    @Mock
    private EnrollmentRepository enrollmentRepository;

    @InjectMocks
    private EnrollmentServiceImpl enrollmentService;

    private Enrollment enrollment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        enrollment = new Enrollment();
        enrollment.setId(1L);
        enrollment.setEnrollmentDate(LocalDate.now());
        enrollment.setGrade("A");
        enrollment.setSemester("Spring 2024");
    }

    @Test
    void testSaveEnrollment() {
        when(enrollmentRepository.save(enrollment)).thenReturn(enrollment);
        Enrollment saved = enrollmentService.saveEnrollment(enrollment);
        assertEquals("A", saved.getGrade());
    }

    @Test
    void testGetEnrollmentById_Found() {
        when(enrollmentRepository.findById(1L)).thenReturn(Optional.of(enrollment));
        Enrollment found = enrollmentService.getEnrollmentById(1L);
        assertEquals("A", found.getGrade());
    }

    @Test
    void testGetEnrollmentById_NotFound() {
        when(enrollmentRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> enrollmentService.getEnrollmentById(2L));
    }

    @Test
    void testGetAllEnrollments() {
        when(enrollmentRepository.findAll()).thenReturn(Arrays.asList(enrollment));
        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
        assertEquals(1, enrollments.size());
    }

    @Test
    void testUpdateEnrollment() {
        Enrollment updated = new Enrollment();
        updated.setGrade("B");
        updated.setSemester("Fall 2024");
        updated.setEnrollmentDate(LocalDate.now());
        when(enrollmentRepository.findById(1L)).thenReturn(Optional.of(enrollment));
        when(enrollmentRepository.save(any(Enrollment.class))).thenReturn(updated);
        Enrollment result = enrollmentService.updateEnrollment(1L, updated);
        assertEquals("B", result.getGrade());
        assertEquals("Fall 2024", result.getSemester());
    }

    @Test
    void testDeleteEnrollment() {
        when(enrollmentRepository.findById(1L)).thenReturn(Optional.of(enrollment));
        doNothing().when(enrollmentRepository).delete(enrollment);
        assertDoesNotThrow(() -> enrollmentService.deleteEnrollment(1L));
    }
}

