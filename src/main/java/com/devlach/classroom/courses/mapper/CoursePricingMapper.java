package com.devlach.classroom.courses.mapper;

import com.devlach.classroom.courses.dto.CoursePricingDTO;
import com.devlach.classroom.courses.dto.CreateUpdateCoursePricing;
import com.devlach.classroom.entity.Course;
import com.devlach.classroom.entity.CoursePricing;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CoursePricingMapper {

    public static ToCoursePricingEntity map(CreateUpdateCoursePricing dto, Long courseId) {
        var coursePricing = new CoursePricing();
        coursePricing.setPriceTrial(dto.priceTrial());
        coursePricing.setPricePerHour(dto.pricePerHour());
        var course = new Course();
        course.setId(courseId);
        coursePricing.setCourse(course);
        return () -> coursePricing;
    }

    public static ToCoursePricingDTO map(CoursePricing coursePricing) {
        return () -> new CoursePricingDTO(
                coursePricing.getId(),
                coursePricing.getPricePerHour().toString(),
                coursePricing.getPriceTrial().toString()
        );
    }
}
