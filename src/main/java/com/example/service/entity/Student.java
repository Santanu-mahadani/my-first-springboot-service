package com.example.service.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String email;
    private String phone;
    private String address;
    
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    
    @OneToMany(mappedBy = "student")
    private Set<Enrollment> enrollments = new HashSet<>();
}
