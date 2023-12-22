package com.devlach.classroom.schedule.dto.regular;

import java.util.List;

public record CreateRegularAvailabilityBatchDTO(
        List<CreateUpdateRegularAvailabilityDTO> regularAvailabilities
) {

    public void validate() {
        regularAvailabilities.forEach(CreateUpdateRegularAvailabilityDTO::validateCreate);
    }
}
