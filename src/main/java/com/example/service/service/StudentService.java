package com.example.service.service;

import com.example.service.entity.Student;
import java.util.List;

public interface StudentService {
    Student saveStudent(Student student);
    Student getStudentById(Long id);
    List<Student> getAllStudents();
    Student updateStudent(Long id, Student student);
    void deleteStudent(Long id);
}
