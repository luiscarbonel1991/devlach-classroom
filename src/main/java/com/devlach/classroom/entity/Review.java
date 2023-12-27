package com.devlach.classroom.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name = "review")
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private CourseClass courseClass;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_profile_id")
    private Profile studentProfile;

    private int rating;
    private String comment;

    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @PrePersist
    private void prePersist() {
        if (createdAt == null) {
            createdAt = Instant.now();
        }
    }

    @PreUpdate
    private void preUpdate() {
        updatedAt = Instant.now();
    }

}
