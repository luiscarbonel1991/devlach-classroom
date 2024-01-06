package com.devlach.classroom.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "category")
@Data
public class Category {

    public static final Integer DEFAULT_CATEGORY_ID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonBackReference
    private Category parent;


    @OneToMany(mappedBy = "parent", cascade = CascadeType.REFRESH)
    @JsonManagedReference
    private List<Category> subcategories = new ArrayList<>();

    @OneToMany(mappedBy = "category", cascade = CascadeType.REFRESH)
    @JsonManagedReference
    private List<Course> courses = new ArrayList<>();

    private Instant createdAt;
    private Instant updatedAt;

    public boolean isRoot() {
        return parent == null;
    }

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

    @PreRemove
    private void preRemove() {
        subcategories.forEach(subcategory -> subcategory.setParent(getDefaultParent(null)));
    }

    public static Category getDefaultParent(Integer id) {
        Category category = new Category();
        category.setId(Objects.requireNonNullElse(id, 1));
        return category;
    }
}
