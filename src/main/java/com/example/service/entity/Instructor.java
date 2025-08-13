package com.example.service.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String email;
    private String phone;
    private String specialization;
    
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    
    @OneToMany(mappedBy = "instructor")
    private Set<Course> courses = new HashSet<>();
}
