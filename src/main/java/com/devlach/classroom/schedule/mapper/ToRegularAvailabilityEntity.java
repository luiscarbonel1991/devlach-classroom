package com.devlach.classroom.schedule.mapper;

import com.devlach.classroom.entity.RegularAvailability;

@FunctionalInterface
public interface ToRegularAvailabilityEntity {

    RegularAvailability toEntity();
}
