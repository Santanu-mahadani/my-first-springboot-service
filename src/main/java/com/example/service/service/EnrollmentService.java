package com.example.service.service;

import com.example.service.entity.Enrollment;
import java.util.List;

public interface EnrollmentService {
    Enrollment saveEnrollment(Enrollment enrollment);
    Enrollment getEnrollmentById(Long id);
    List<Enrollment> getAllEnrollments();
    Enrollment updateEnrollment(Long id, Enrollment enrollment);
    void deleteEnrollment(Long id);
}

