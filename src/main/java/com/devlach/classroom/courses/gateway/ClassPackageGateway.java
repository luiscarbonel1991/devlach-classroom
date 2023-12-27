package com.devlach.classroom.courses.gateway;

import com.devlach.classroom.courses.dto.ClassPackageDTO;
import com.devlach.classroom.courses.dto.CreateUpdateClassPackageDTO;

public interface ClassPackageGateway {

    ClassPackageDTO create(CreateUpdateClassPackageDTO dto, String email);
}
