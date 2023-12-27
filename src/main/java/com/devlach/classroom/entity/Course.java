package com.devlach.classroom.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "course")
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;

    private String title;

    private String description;

    private String image;

    private String video;

    private String level;

    private String language;

    private String category;

    private boolean active;

    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "deleted_at")
    private Instant deletedAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
    @OrderBy("createdAt DESC")
    @JsonManagedReference
    private List<CoursePricing> pricing;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_profile_id")
    private Profile teacherProfile;
}
