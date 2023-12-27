package com.devlach.classroom.courses.dto;

import com.devlach.classroom.api.exception.BadRequestException;

import java.math.BigDecimal;

public record CreateUpdateCoursePricing(
        Long id,
        BigDecimal pricePerHour,
        BigDecimal priceTrial
) {
     public CreateUpdateCoursePricing (BigDecimal pricePerHour, BigDecimal priceTrial) {
        this(null, pricePerHour, priceTrial);
    }

    public void validate() {
        if (pricePerHour == null) {
            throw BadRequestException.requiredField("pricePerHour");
        }
        if (priceTrial == null) {
            throw BadRequestException.requiredField("priceTrial");
        }

        if (pricePerHour.compareTo(priceTrial) < 0) {
            throw BadRequestException.pricePerHourMustBeGreaterThanPriceTrial(pricePerHour.doubleValue(), pricePerHour.doubleValue());
        }

        if (pricePerHour.compareTo(BigDecimal.ZERO) < 0) {
            throw BadRequestException.pricePerHourMustBeGreaterThanZero(pricePerHour.doubleValue());
        }

        if (priceTrial.compareTo(BigDecimal.ZERO) < 0) {
            throw BadRequestException.priceTrialMustBeGreaterThanZero(priceTrial.doubleValue());
        }
    }
}
