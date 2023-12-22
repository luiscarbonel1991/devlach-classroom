package com.devlach.classroom.schedule.dto;

import java.util.List;

public record CreateWeeklyAvailabilityBatchDTO(
        List<CreateUpdateWeeklyAvailabilityDTO> weeklyAvailabilities
) {

    public void validate() {
        weeklyAvailabilities.forEach(CreateUpdateWeeklyAvailabilityDTO::validateCreate);
    }
}
