package com.devlach.classroom.courses.mapper;

import com.devlach.classroom.entity.Course;

@FunctionalInterface
public interface ToCourseEntity {

    Course toEntity();
}
