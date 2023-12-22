package com.devlach.classroom.schedule.dto.weekly;

import java.util.List;

public record UpdateWeeklyAvailabilityBatchDTO(
        List<CreateUpdateWeeklyAvailabilityDTO> weeklyAvailabilities
) {

    public void validate() {
        weeklyAvailabilities.forEach(CreateUpdateWeeklyAvailabilityDTO::validateUpdate);
    }

    public List<Long> ids() {
        return weeklyAvailabilities.stream()
                .map(CreateUpdateWeeklyAvailabilityDTO::id)
                .toList();
    }
}
