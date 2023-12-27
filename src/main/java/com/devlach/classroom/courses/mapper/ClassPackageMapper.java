package com.devlach.classroom.courses.mapper;

import com.devlach.classroom.courses.dto.ClassPackageDTO;
import com.devlach.classroom.courses.dto.CreateUpdateClassPackageDTO;
import com.devlach.classroom.entity.ClassPackage;
import com.devlach.classroom.entity.ClassPackageStatusType;
import com.devlach.classroom.entity.CoursePricing;
import com.devlach.classroom.entity.Profile;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ClassPackageMapper {

    public ToClassPackageEntity map(CreateUpdateClassPackageDTO dto, Long studentId, Long coursePricingId) {
        return () -> {
            var classPackage = new ClassPackage();
            var student = new Profile();
            student.setId(studentId);
            classPackage.setStudentProfile(student);
            var coursePricing = new CoursePricing();
            coursePricing.setId(coursePricingId);
            classPackage.setCoursePricing(coursePricing);
            classPackage.setStatus(ClassPackageStatusType.of(dto.status()));
            classPackage.setNumberOfClasses(dto.numberOfClasses());
            classPackage.setDurationInMinutes(dto.durationInMinutes());
            classPackage.setTrial(dto.isTrial());
            classPackage.setTitle(dto.title());
            return classPackage;
        };
    }

    public ToClassPackageDTO map(ClassPackage classPackage) {
        return () -> new ClassPackageDTO(
                classPackage.getId(),
                classPackage.getTitle(),
                classPackage.getNumberOfClasses(),
                classPackage.getDurationInMinutes(),
                classPackage.isTrial()
        );
    }
}
