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


    @Column(name = "is_published", nullable = false)
    private boolean isPublished;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_attachment_id")
    private Attachment imageAttachment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public Course copy() {
        var course = new Course();
        course.setId(this.id);
        course.setTitle(this.title);
        course.setDescription(this.description);
        course.setImage(this.image);
        course.setVideo(this.video);
        course.setLevel(this.level);
        course.setLanguage(this.language);
        course.setCategory(this.category);
        course.setPublished(this.isPublished);
        course.setCreatedAt(this.createdAt);
        course.setUpdatedAt(this.updatedAt);
        course.setDeletedAt(this.deletedAt);
        course.setPricing(this.pricing);
        course.setTeacherProfile(this.teacherProfile);
        course.setImageAttachment(this.imageAttachment);
        return course;
    }
}
