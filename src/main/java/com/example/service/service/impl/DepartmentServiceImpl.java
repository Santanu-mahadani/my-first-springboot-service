package com.example.service.service.impl;

import com.example.service.entity.Department;
import com.example.service.exception.ResourceNotFoundException;
import com.example.service.repository.DepartmentRepository;
import com.example.service.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);
    private final DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        logger.info("Saving department: {}", department.getName());
        return departmentRepository.save(department);
    }

    @Override
    public Department getDepartmentById(Long id) {
        logger.info("Fetching department by id: {}", id);
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
    }

    @Override
    public List<Department> getAllDepartments() {
        logger.info("Fetching all departments");
        return departmentRepository.findAll();
    }

    @Override
    public Department updateDepartment(Long id, Department departmentDetails) {
        Department department = getDepartmentById(id);
        department.setName(departmentDetails.getName());
        department.setLocation(departmentDetails.getLocation());
        logger.info("Updating department: {}", id);
        return departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(Long id) {
        Department department = getDepartmentById(id);
        logger.info("Deleting department: {}", id);
        departmentRepository.delete(department);
    }
}

