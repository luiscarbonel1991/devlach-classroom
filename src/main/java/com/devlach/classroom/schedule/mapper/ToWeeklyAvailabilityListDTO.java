package com.devlach.classroom.schedule.mapper;

import com.devlach.classroom.schedule.dto.WeeklyAvailabilityDTO;

import java.util.List;

@FunctionalInterface
public interface ToWeeklyAvailabilityListDTO {
    List<WeeklyAvailabilityDTO> toListDTO();
}
