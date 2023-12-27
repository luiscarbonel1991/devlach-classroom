package com.devlach.classroom.courses.mapper;

import com.devlach.classroom.courses.dto.CourseDTO;

@FunctionalInterface
public interface ToCourseDTO {

    CourseDTO toDTO();
}
