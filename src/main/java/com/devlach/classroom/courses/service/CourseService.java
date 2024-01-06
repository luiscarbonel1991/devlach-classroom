package com.devlach.classroom.courses.service;

import com.devlach.classroom.api.exception.NotFoundException;
import com.devlach.classroom.courses.dto.CreateUpdateCourseDTO;
import com.devlach.classroom.courses.mapper.CourseMapper;
import com.devlach.classroom.courses.mapper.CoursePricingMapper;
import com.devlach.classroom.courses.persistence.repository.CoursePricingRepository;
import com.devlach.classroom.courses.persistence.repository.CourseRepository;
import com.devlach.classroom.entity.Attachment;
import com.devlach.classroom.entity.Category;
import com.devlach.classroom.entity.Course;
import com.devlach.classroom.entity.CoursePricing;
import com.devlach.classroom.users.dto.ProfileDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    private final CoursePricingRepository coursePricingRepository;

    public CourseService(CourseRepository courseRepository, CoursePricingRepository coursePricingRepository) {
        this.courseRepository = courseRepository;
        this.coursePricingRepository = coursePricingRepository;
    }

    public List<Course> findAll(ProfileDTO profile) {
        return courseRepository.findAllByTeacherProfileIdAndDeletedAtIsNull(profile.id());
    }

    @Transactional
    public Course create(CreateUpdateCourseDTO dto, ProfileDTO profile) {
        dto.validateCreate();
        var course = CourseMapper.map(dto, profile.id()).toEntity();
        course = courseRepository.save(course);
        var coursePricing = CoursePricingMapper.map(dto.pricing(), course.getId()).toEntity();
        var coursePricingSaved = coursePricingRepository.save(coursePricing);
        course.setPricing(List.of(coursePricingSaved));
        return course;
    }

    @Transactional
    public Course createDraft(CreateUpdateCourseDTO dto, ProfileDTO profile) {
        dto.validateCreateDraft();
        var course = CourseMapper.map(dto, profile.id()).toEntity();
        course = courseRepository.save(course);
        return course;
    }

    public Course findByCourseIdAndTeacherId(Long courseId, Long teacherId) {
        return Optional.ofNullable(courseRepository.findByIdAndTeacherProfileIdAndDeletedAtIsNull(courseId, teacherId))
                .orElseThrow(() -> NotFoundException.courseId(courseId));
    }

    @Transactional
    public Course update(Long courseId, CreateUpdateCourseDTO courseDTO, ProfileDTO profile) {
        var currentCourse = findByCourseIdAndTeacherId(courseId, profile.id());
        if (courseDTO.hasTitle()) {
            currentCourse.setTitle(courseDTO.title());
        }
        if (courseDTO.hasDescription()) {
            currentCourse.setDescription(courseDTO.description());
        }
        if(courseDTO.hasVideoUrl()) {
            currentCourse.setVideo(courseDTO.videoUrl());
        }

        if (courseDTO.hasPricing()) {
            var coursePricingToCreate = CoursePricingMapper.map(courseDTO.pricing(), courseId).toEntity();
            var coursePricingCreated = coursePricingRepository.save(coursePricingToCreate);
            var currentList = new ArrayList<>(currentCourse.getPricing().stream().filter(coursePricing -> !Objects.equals(coursePricing.getId(), coursePricingCreated.getId())).toList());
            currentList.add(coursePricingCreated);
            currentList.sort(Comparator.comparing(CoursePricing::getCreatedAt).reversed());
            currentCourse.setPricing(currentList);
        }

        if(courseDTO.hasCategoryId()) {
            var category = new Category();
            category.setId(courseDTO.categoryId());
            currentCourse.setCategory(category);
        }

        return courseRepository.save(currentCourse);
    }

    public void delete(Long courseId, Long teacherId) {
        var course = findByCourseIdAndTeacherId(courseId, teacherId);
        course.setDeletedAt(Instant.now());
        courseRepository.save(course);
    }

    public CoursePricing findCoursePricingById(Long coursePricingId) {
        return coursePricingRepository.findById(coursePricingId)
                .orElseThrow(() -> NotFoundException.coursePricingId(coursePricingId));
    }

    public Course unpublished(Long courseId, Long id) {
        var course = findByCourseIdAndTeacherId(courseId, id);
        course.setPublished(false);
        return courseRepository.save(course);
    }

    public Course publish(Long courseId, Long teacherId) {
        var course = findByCourseIdAndTeacherId(courseId, teacherId);
        course.setPublished(true);
        return courseRepository.save(course);
    }

    @Transactional
    public Map<String, Course> uploadImage(Long courseId, Long teacherId, Long attachmentId) {
        var course = findByCourseIdAndTeacherId(courseId, teacherId);
        var attachment = new Attachment();
        attachment.setId(attachmentId);
        var map = new HashMap<String, Course>();
        map.put("oldCourse", course.copy());
        course.setImageAttachment(attachment);
        map.put("newCourse", courseRepository.save(course));
        return map;
    }
}
