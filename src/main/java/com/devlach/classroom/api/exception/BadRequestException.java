package com.devlach.classroom.api.exception;

import com.devlach.classroom.utils.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends AppException {

    public BadRequestException(ErrorCode errorCode, String message) {
        super(errorCode, message);
        super.errors.add(message);
    }

    public static BadRequestException invalidWeeklyQuantityDays(int quantityDays) {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_INVALID_WEEKLY_QUANTITY_DAYS;
        String message = String.format("%s. Quantity days must be between 1 and 7. Quantity days: %s", errorCode.getMessageCode(), quantityDays);
        return new BadRequestException(errorCode, message);
    }

    public static BadRequestException invalidWeeklyStartTimeFormat(String startTime) {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_INVALID_WEEKLY_START_TIME;
        String message = String.format("%s. Invalid startTime format. Expected: %s. Received: %s", errorCode.getMessageCode(), DateUtils.AVAILABILITY_TIME_FORMAT, startTime);
        return new BadRequestException(errorCode, message);
    }

    public static BadRequestException invalidWeeklyEndTimeFormat(String startTime) {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_INVALID_WEEKLY_END_TIME;
        String message = String.format("%s. Invalid endTime format. Expected: %s. Received: %s", errorCode.getMessageCode(), DateUtils.AVAILABILITY_TIME_FORMAT, startTime);
        return new BadRequestException(errorCode, message);
    }

    public static BadRequestException requiredField(String fieldName) {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_REQUIRED_FIELD;
        String message = String.format("%s. Field name: %s", errorCode.getMessageCode(), fieldName);
        return new BadRequestException(errorCode, message);
    }

    public static BadRequestException startTimeMustBeBeforeEndTime(String startTime, String endTime) {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_STAR_TIME_MUST_BE_BEFORE_END_TIME;
        String message = String.format("%s. Start time: %s, End time: %s", errorCode.getMessageCode(), startTime, endTime);
        return new BadRequestException(errorCode, message);
    }

    public static BadRequestException dateMustBeInTheFuture(String date) {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_DATE_MUST_BE_IN_THE_FUTURE;
        String message = String.format("%s. Date: %s", errorCode.getMessageCode(), date);
        return new BadRequestException(errorCode, message);
    }
}


