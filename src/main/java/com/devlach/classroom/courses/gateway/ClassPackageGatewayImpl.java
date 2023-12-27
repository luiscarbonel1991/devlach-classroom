package com.devlach.classroom.courses.gateway;

import com.devlach.classroom.courses.dto.ClassPackageDTO;
import com.devlach.classroom.courses.dto.CreateUpdateClassPackageDTO;
import com.devlach.classroom.courses.mapper.ClassPackageMapper;
import com.devlach.classroom.courses.service.ClassPackageService;
import com.devlach.classroom.courses.service.CourseService;
import com.devlach.classroom.entity.ProfileType;
import com.devlach.classroom.users.gateway.UserGateway;
import org.springframework.stereotype.Component;

@Component
public class ClassPackageGatewayImpl implements ClassPackageGateway {

    private final UserGateway userGateway;

    private final CourseService courseService;

    private final ClassPackageService classPackageService;

    public ClassPackageGatewayImpl(UserGateway userGateway, CourseService courseService, ClassPackageService classPackageService) {
        this.userGateway = userGateway;
        this.courseService = courseService;
        this.classPackageService = classPackageService;
    }

    @Override
    public ClassPackageDTO create(CreateUpdateClassPackageDTO dto, String email){
        dto.validateCreate();
        var student = userGateway.findProfileByEmail(email, ProfileType.STUDENT);
        var coursePricing =courseService.findCoursePricingById(dto.coursePricingId());
        return ClassPackageMapper.map(classPackageService.create(dto, student.id(), coursePricing.getId())).toDTO();
    }
}
