package com.example.service.controller;

import com.example.service.entity.Instructor;
import com.example.service.service.InstructorService;
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

class InstructorControllerTest {
    private MockMvc mockMvc;

    @Mock
    private InstructorService instructorService;

    @InjectMocks
    private InstructorController instructorController;

    private Instructor instructor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(instructorController).build();
        instructor = new Instructor();
        instructor.setId(1L);
        instructor.setName("Dr. Alice");
        instructor.setEmail("alice@university.edu");
    }

    @Test
    void testGetAllInstructors() throws Exception {
        List<Instructor> instructors = Arrays.asList(instructor);
        when(instructorService.getAllInstructors()).thenReturn(instructors);
        mockMvc.perform(get("/api/instructors"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetInstructorById() throws Exception {
        when(instructorService.getInstructorById(1L)).thenReturn(instructor);
        mockMvc.perform(get("/api/instructors/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateInstructor() throws Exception {
        when(instructorService.saveInstructor(any(Instructor.class))).thenReturn(instructor);
        mockMvc.perform(post("/api/instructors")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Dr. Alice\",\"email\":\"alice@university.edu\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateInstructor() throws Exception {
        when(instructorService.updateInstructor(eq(1L), any(Instructor.class))).thenReturn(instructor);
        mockMvc.perform(put("/api/instructors/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Dr. Alice\",\"email\":\"alice@university.edu\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteInstructor() throws Exception {
        doNothing().when(instructorService).deleteInstructor(1L);
        mockMvc.perform(delete("/api/instructors/1"))
                .andExpect(status().isNoContent());
    }
}

