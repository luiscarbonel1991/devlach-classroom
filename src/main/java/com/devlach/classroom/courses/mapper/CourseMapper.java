package com.devlach.classroom.courses.mapper;

import com.devlach.classroom.courses.dto.CourseDTO;
import com.devlach.classroom.courses.dto.CreateUpdateCourseDTO;
import com.devlach.classroom.entity.Course;
import com.devlach.classroom.entity.Profile;
import lombok.experimental.UtilityClass;

import java.util.Collections;

@UtilityClass
public class CourseMapper {

    public static ToCourseEntity map(CreateUpdateCourseDTO dto, Long teacherProfileId) {
        var course = new Course();
        course.setId(dto.id());
        course.setTitle(dto.title());
        course.setDescription(dto.description());
        var profile = new Profile();
        profile.setId(teacherProfileId);
        course.setTeacherProfile(profile);
        return () -> course;
    }

    public static ToCourseDTO map(Course course) {
        return () -> new CourseDTO(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.isPublished(),
                course.getImage(),
                course.getVideo(),
                course.getPricing() != null ?
                        course.getPricing().stream()
                                .map(CoursePricingMapper::map)
                                .map(ToCoursePricingDTO::toDTO)
                                .toList()
                        : Collections.emptyList()
        );
    }
}
