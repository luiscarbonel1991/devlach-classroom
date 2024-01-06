package com.devlach.classroom.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name = "image")
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "entity_type")
    private EntityType entityType;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "extension")
    private ImageExtensionType extension;

    private Long size;

    @Column(name = "path", nullable = false)
    private String path;
    private Instant createdAt;
    private Instant associatedAt;
    private Instant deletedAt;
}
