package com.devlach.classroom.api.exception;

import com.devlach.classroom.entity.ClassPackageDurationType;
import com.devlach.classroom.entity.ClassPackageStatusType;
import com.devlach.classroom.schedule.enums.DayOfWeek;

public enum ErrorCode {
    // 400
    INVALID_REQUEST("Invalid request"),
    BAD_REQUEST_REQUIRED_FIELD("Required field"),
    BAD_REQUEST_INVALID_WEEKLY_QUANTITY_DAYS("Invalid weekly quantity days"),

    BAD_REQUEST_INVALID_WEEKLY_DATE("Invalid weekly date format"),
    BAD_REQUEST_INVALID_WEEKLY_START_TIME("Invalid weekly start time"),
    BAD_REQUEST_INVALID_WEEKLY_END_TIME("Invalid weekly end time"),
    BAD_REQUEST_STAR_TIME_MUST_BE_BEFORE_END_TIME("Start time must be before end time"),
    BAD_REQUEST_DATE_MUST_BE_IN_THE_FUTURE("Date must be in the future"),

    BAD_REQUEST_INVALID_CLASS_PACKAGE_DURATION("Invalid class package duration. Expected: " + ClassPackageDurationType.durationsAsString()),

    BAD_REQUEST_INVALID_DAY_OF_WEEK("Invalid day of week. Expected: " + DayOfWeek.daysOfWeekAsString()),
    BAD_REQUEST_INVALID_CLASS_PACKAGE_STATUS("Invalid class package status. Expected: " + ClassPackageStatusType.statusAsString()),

    BAD_REQUEST_PRICE_PER_HOUR_MUST_BE_GREATER_THAN_ZERO("Price per hour must be greater than zero"),

    BAD_REQUEST_PRICE_TRIAL_MUST_BE_GREATER_THAN_ZERO("Price trial must be greater than zero"),
    BAD_REQUEST_PRICE_PER_HOUR_MUST_BE_GREATER_THAN_PRICE_TRIAL("Price per hour must be greater than price trial"),
    BAD_REQUEST_INVALID_FIELD("Invalid field"),
    // 404
    RESOURCE_NOT_FOUND("Resource not found"),
    NOT_FOUND_WEEKLY_AVAILABILITY_ID("Not found weekly availability id"),
    NOT_FOUND_REGULAR_AVAILABILITY_ID("Not found regular availability id"),
    NOT_FOUND_USER_EMAIL("Not found user email"),
    NOT_FOUND_USER_PROFILE("Not found user profile"),
    NOT_FOUND_COURSE_ID("Not found course id"),
    NOT_FOUND_COURSE_PRICING_ID("Not found course pricing id"),
    // 409
    CONFLICT("Conflict"),
    CONFLICT_WEEKLY_AVAILABILITY_OVERLAP("There is already an availability for the same date and time overlap"),
    CONFLICT_REGULAR_AVAILABILITY_OVERLAP("There is already a regular availability for this time"),
    CONFLICT_USER_EMAIL_ALREADY_EXISTS("Email already exists"),
    // 500
    INTERNAL_SERVER_ERROR("Internal server error");


    private final String messageCode;

    ErrorCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getMessageCode() {
        return messageCode;
    }
}
