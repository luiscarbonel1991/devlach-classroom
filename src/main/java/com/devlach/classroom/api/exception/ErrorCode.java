package com.devlach.classroom.api.exception;

public enum ErrorCode {
    // 400
    INVALID_REQUEST("Invalid request"),
    BAD_REQUEST_REQUIRED_FIELD("Required field"),
    BAD_REQUEST_INVALID_WEEKLY_QUANTITY_DAYS("Invalid weekly quantity days"),
    BAD_REQUEST_INVALID_WEEKLY_START_TIME("Invalid weekly start time"),
    BAD_REQUEST_INVALID_WEEKLY_END_TIME("Invalid weekly end time"),
    BAD_REQUEST_STAR_TIME_MUST_BE_BEFORE_END_TIME("Start time must be before end time"),
    BAD_REQUEST_DATE_MUST_BE_IN_THE_FUTURE("Date must be in the future"),

    // 404
    RESOURCE_NOT_FOUND("Resource not found"),
    NOT_FOUND_WEEKLY_AVAILABILITY_ID("Not found weekly availability id"),
    NOT_FOUND_REGULAR_AVAILABILITY_ID("Not found regular availability id"),
    NOT_FOUND_USER_EMAIL("Not found user email"),
    NOT_FOUND_USER_PROFILE("Not found user profile"),
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
