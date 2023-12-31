package com.devlach.classroom.courses.gateway;

import com.devlach.classroom.courses.dto.CourseDTO;
import com.devlach.classroom.courses.dto.CreateUpdateCourseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseGateway {

    List<CourseDTO> findAll(String ownerEmail);

    CourseDTO create(CreateUpdateCourseDTO createUpdateCourseDTO, String ownerEmail);
    CourseDTO createDraft(CreateUpdateCourseDTO createUpdateCourseDTO, String ownerEmail);

    CourseDTO findById(Long courseId, String ownerEmail);

    CourseDTO update(Long courseId, CreateUpdateCourseDTO courseDTO, String ownerEmail);

    void delete(Long courseId, String ownerEmail);

    CourseDTO unpublished(Long courseId, String ownerEmail);

    CourseDTO publish(Long courseId, String ownerEmail);

    CourseDTO uploadImage(Long courseId, String owner, String name, String description, MultipartFile file);
}
