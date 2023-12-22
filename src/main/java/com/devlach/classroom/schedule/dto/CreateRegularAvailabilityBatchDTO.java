package com.devlach.classroom.schedule.dto;

import java.util.List;

public record CreateRegularAvailabilityBatchDTO(
        List<CreateUpdateRegularAvailabilityDTO> regularAvailabilities
) {
    
}
