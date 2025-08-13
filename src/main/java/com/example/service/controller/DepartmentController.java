package com.example.service.controller;

import com.example.service.entity.Department;
import com.example.service.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
    private final DepartmentService departmentService;

    @GetMapping
    public List<Department> getAllDepartments() {
        logger.info("Fetching all departments");
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable Long id) {
        logger.info("Fetching department by id: {}", id);
        return departmentService.getDepartmentById(id);
    }

    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        logger.info("Creating new department");
        return departmentService.saveDepartment(department);
    }

    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable Long id, @RequestBody Department department) {
        logger.info("Updating department with id: {}", id);
        return departmentService.updateDepartment(id, department);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        logger.info("Deleting department with id: {}", id);
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}
