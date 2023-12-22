package com.devlach.classroom.schedule.mapper;

import com.devlach.classroom.schedule.dto.regular.RegularAvailabilityDTO;

import java.util.List;

@FunctionalInterface
public interface ToRegularAvailabilityListDTO {

    List<RegularAvailabilityDTO> toListDTO();
}
