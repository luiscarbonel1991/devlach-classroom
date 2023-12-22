package com.devlach.classroom.schedule.mapper;

import com.devlach.classroom.schedule.dto.weekly.WeeklyAvailabilityDTO;

@FunctionalInterface
public interface ToWeeklyAvailabilityDTO {
    WeeklyAvailabilityDTO toDTO();
}
