package com.devlach.classroom.courses.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record CoursePricingDTO(
        Long id,
        BigDecimal pricePerHour,
        BigDecimal priceTrial,
        Instant createdAt
) {
}
