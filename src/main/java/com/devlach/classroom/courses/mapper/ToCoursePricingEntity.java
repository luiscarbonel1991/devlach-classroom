package com.devlach.classroom.courses.mapper;

import com.devlach.classroom.entity.CoursePricing;

@FunctionalInterface
public interface ToCoursePricingEntity {

    CoursePricing toEntity();
}
