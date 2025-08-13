package com.example.service.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String code;
    private Integer credits;
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;
    
    @OneToMany(mappedBy = "course")
    private Set<Enrollment> enrollments = new HashSet<>();
}
