package com.example.service.controller;

import com.example.service.entity.Department;
import com.example.service.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class DepartmentControllerTest {
    private MockMvc mockMvc;

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    private Department department;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(departmentController).build();
        department = new Department();
        department.setId(1L);
        department.setName("Computer Science");
    }

    @Test
    void testGetAllDepartments() throws Exception {
        List<Department> departments = Arrays.asList(department);
        when(departmentService.getAllDepartments()).thenReturn(departments);
        mockMvc.perform(get("/api/departments"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetDepartmentById() throws Exception {
        when(departmentService.getDepartmentById(1L)).thenReturn(department);
        mockMvc.perform(get("/api/departments/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateDepartment() throws Exception {
        when(departmentService.saveDepartment(any(Department.class))).thenReturn(department);
        mockMvc.perform(post("/api/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Computer Science\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateDepartment() throws Exception {
        when(departmentService.updateDepartment(eq(1L), any(Department.class))).thenReturn(department);
        mockMvc.perform(put("/api/departments/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Computer Science\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteDepartment() throws Exception {
        doNothing().when(departmentService).deleteDepartment(1L);
        mockMvc.perform(delete("/api/departments/1"))
                .andExpect(status().isNoContent());
    }
}

