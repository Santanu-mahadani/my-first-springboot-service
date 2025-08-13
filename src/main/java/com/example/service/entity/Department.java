package com.example.service.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String location;
    
    @OneToMany(mappedBy = "department")
    private Set<Student> students = new HashSet<>();
    
    @OneToMany(mappedBy = "department")
    private Set<Instructor> instructors = new HashSet<>();
}
