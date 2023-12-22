package com.devlach.classroom.schedule.mapper;

import com.devlach.classroom.schedule.dto.RegularAvailabilityDTO;

@FunctionalInterface
public interface ToRegularAvailabilityDTO {

    RegularAvailabilityDTO toDTO();
}
