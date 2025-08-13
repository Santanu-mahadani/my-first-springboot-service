package com.example.service.service;

import com.example.service.entity.Department;
import java.util.List;

public interface DepartmentService {
    Department saveDepartment(Department department);
    Department getDepartmentById(Long id);
    List<Department> getAllDepartments();
    Department updateDepartment(Long id, Department department);
    void deleteDepartment(Long id);
}

