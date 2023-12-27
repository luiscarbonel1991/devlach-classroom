package com.devlach.classroom.courses.mapper;

import com.devlach.classroom.courses.dto.ClassPackageDTO;

@FunctionalInterface
public interface ToClassPackageDTO {

    ClassPackageDTO toDTO();
}
