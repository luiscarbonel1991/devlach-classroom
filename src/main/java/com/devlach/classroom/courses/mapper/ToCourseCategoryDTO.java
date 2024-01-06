package com.devlach.classroom.courses.mapper;

import com.devlach.classroom.courses.dto.CourseCategoryDTO;

@FunctionalInterface
public interface ToCourseCategoryDTO {

    CourseCategoryDTO toDTO();
}
