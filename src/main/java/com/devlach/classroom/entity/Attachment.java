package com.devlach.classroom.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "attachment")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attachment_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "attachment_type")
    private AttachmentType type;

    @Column(name = "name")
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "extension")
    private FileExtensionType extension;

    private Long size;

    @Column(name = "path", nullable = false)
    private String path;

    @Column(name = "owner", nullable = false)
    private Long owner;

    @Column(name = "created_at", updatable = false)
    private Instant createdAt;
    private Instant associatedAt;
    private Instant deletedAt;

    @PrePersist
    private void prePersist() {
        if(createdAt == null) {
            createdAt = Instant.now();
        }
    }

    @PreUpdate
    private void preUpdate() {
        if(associatedAt == null) {
            associatedAt = Instant.now();
        }
    }
}
