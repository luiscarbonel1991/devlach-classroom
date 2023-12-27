package com.devlach.classroom.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name = "profile")
@Data
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long id;

    @Column( name = "full_name")
    private String fullName;

    @Column(name = "bio")
    private String bio;

    @Column(name = "profile_pic_url")
    private String profilePictureUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "profile_type")
    private ProfileType type;

    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "deleted_at")
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
