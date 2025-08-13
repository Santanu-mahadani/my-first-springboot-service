package com.example.service.service.impl;

import com.example.service.entity.Enrollment;
import com.example.service.exception.ResourceNotFoundException;
import com.example.service.repository.EnrollmentRepository;
import com.example.service.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {
    private static final Logger logger = LoggerFactory.getLogger(EnrollmentServiceImpl.class);
    private final EnrollmentRepository enrollmentRepository;

    @Override
    public Enrollment saveEnrollment(Enrollment enrollment) {
        logger.info("Saving enrollment for student: {} in course: {}", 
            enrollment.getStudent() != null ? enrollment.getStudent().getId() : null, 
            enrollment.getCourse() != null ? enrollment.getCourse().getId() : null);
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public Enrollment getEnrollmentById(Long id) {
        logger.info("Fetching enrollment by id: {}", id);
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id: " + id));
    }

    @Override
    public List<Enrollment> getAllEnrollments() {
        logger.info("Fetching all enrollments");
        return enrollmentRepository.findAll();
    }

    @Override
    public Enrollment updateEnrollment(Long id, Enrollment enrollmentDetails) {
        Enrollment enrollment = getEnrollmentById(id);
        enrollment.setStudent(enrollmentDetails.getStudent());
        enrollment.setCourse(enrollmentDetails.getCourse());
        enrollment.setEnrollmentDate(enrollmentDetails.getEnrollmentDate());
        enrollment.setGrade(enrollmentDetails.getGrade());
        enrollment.setSemester(enrollmentDetails.getSemester());
        logger.info("Updating enrollment: {}", id);
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public void deleteEnrollment(Long id) {
        Enrollment enrollment = getEnrollmentById(id);
        logger.info("Deleting enrollment: {}", id);
        enrollmentRepository.delete(enrollment);
    }
}
