package com.devlach.classroom.schedule.mapper;

import com.devlach.classroom.entity.WeeklyAvailability;

@FunctionalInterface
public interface ToWeeklyAvailabilityEntity {

    WeeklyAvailability toEntity();
}
