package com.devlach.classroom.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "course_pricing")
@Data
public class CoursePricing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_pricing_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    @JsonBackReference
    private Course course;

    @Column(name = "price_per_hour", updatable = false, precision = 10, scale = 2)
    private BigDecimal pricePerHour;

    @Column(name = "price_trial", updatable = false, precision = 10, scale = 2)
    private BigDecimal priceTrial;

    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @PrePersist
    private void prePersist() {
        if (createdAt == null) {
            createdAt = Instant.now();
        }
    }
}
