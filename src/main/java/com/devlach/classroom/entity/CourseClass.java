package com.devlach.classroom.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "course_class")
@Data
public class CourseClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private Long id;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_profile_id")
    private Profile teacherProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_profile_id")
    private Profile studentProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_package_id")
    @JsonBackReference
    private ClassPackage classPackage;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ClassStatusType status;

    private String focus;

    private String curseName;
    private String lastLessonSummary;

    private String link;

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
