package com.devlach.classroom.schedule.mapper;

import com.devlach.classroom.schedule.dto.RegularAvailabilityDTO;

import java.util.List;

@FunctionalInterface
public interface ToRegularAvailabilityListDTO {

    List<RegularAvailabilityDTO> toListDTO();
}
