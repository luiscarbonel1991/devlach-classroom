package com.devlach.classroom.courses.mapper;

import com.devlach.classroom.courses.dto.CoursePricingDTO;

@FunctionalInterface
public interface ToCoursePricingDTO {

    CoursePricingDTO toDTO();
}
