package com.example.service.service.impl;

import com.example.service.entity.Instructor;
import com.example.service.exception.ResourceNotFoundException;
import com.example.service.repository.InstructorRepository;
import com.example.service.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {
    private static final Logger logger = LoggerFactory.getLogger(InstructorServiceImpl.class);
    private final InstructorRepository instructorRepository;

    @Override
    public Instructor saveInstructor(Instructor instructor) {
        logger.info("Saving instructor: {}", instructor.getName());
        return instructorRepository.save(instructor);
    }

    @Override
    public Instructor getInstructorById(Long id) {
        logger.info("Fetching instructor by id: {}", id);
        return instructorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found with id: " + id));
    }

    @Override
    public List<Instructor> getAllInstructors() {
        logger.info("Fetching all instructors");
        return instructorRepository.findAll();
    }

    @Override
    public Instructor updateInstructor(Long id, Instructor instructorDetails) {
        Instructor instructor = getInstructorById(id);
        instructor.setName(instructorDetails.getName());
        instructor.setEmail(instructorDetails.getEmail());
        instructor.setPhone(instructorDetails.getPhone());
        instructor.setSpecialization(instructorDetails.getSpecialization());
        instructor.setDepartment(instructorDetails.getDepartment());
        logger.info("Updating instructor: {}", id);
        return instructorRepository.save(instructor);
    }

    @Override
    public void deleteInstructor(Long id) {
        Instructor instructor = getInstructorById(id);
        logger.info("Deleting instructor: {}", id);
        instructorRepository.delete(instructor);
    }
}

