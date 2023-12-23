package com.devlach.classroom.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "classes")
@Data
public class CourseClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_profile_id")
    private Profile teacherProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_profile_id")
    private Profile studentProfile;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ClassStatusType status;

    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "updated_at")
    private Instant deletedAt;

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
