package com.example.service.service;

import com.example.service.entity.Instructor;
import java.util.List;

public interface InstructorService {
    Instructor saveInstructor(Instructor instructor);
    Instructor getInstructorById(Long id);
    List<Instructor> getAllInstructors();
    Instructor updateInstructor(Long id, Instructor instructor);
    void deleteInstructor(Long id);
}

