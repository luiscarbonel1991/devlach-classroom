package com.devlach.classroom.courses.gateway;

import com.devlach.classroom.courses.dto.CourseDTO;
import com.devlach.classroom.courses.dto.CreateUpdateCourseDTO;

import java.util.List;

public interface CourseGateway {

    List<CourseDTO> findAll(String ownerEmail);

    CourseDTO create(CreateUpdateCourseDTO createUpdateCourseDTO, String ownerEmail);

    CourseDTO findById(Long courseId, String ownerEmail);

    CourseDTO update(Long courseId, CreateUpdateCourseDTO courseDTO, String ownerEmail);

    void delete(Long courseId, String ownerEmail);
}
