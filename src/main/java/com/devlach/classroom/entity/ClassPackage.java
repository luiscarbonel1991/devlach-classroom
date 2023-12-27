package com.devlach.classroom.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "class_package")
@Data
public class ClassPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_package_id")
    private Long id;

    private String title;
    private int numberOfClasses;
    private int remainingClasses;
    private int durationInMinutes;

    @Column(name = "is_trial")
    private boolean isTrial;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ClassPackageStatusType status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_profile_id")
    private Profile studentProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_price_id")
    private CoursePricing coursePricing;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "classPackage")
    @JsonManagedReference
    private List<CourseClass> classes;

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
