package com.devlach.classroom.api.exception;

import com.devlach.classroom.utils.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
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

    public static BadRequestException invalidClassPackageDuration(String duration) {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_INVALID_CLASS_PACKAGE_DURATION;
        String message = String.format("%s. Duration: %s", errorCode.getMessageCode(), duration);
        return new BadRequestException(errorCode, message);
    }

    public static BadRequestException invalidDayOfWeek(String dayOfWeek) {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_INVALID_DAY_OF_WEEK;
        String message = String.format("%s. Day of week: %s", errorCode.getMessageCode(), dayOfWeek);
        return new BadRequestException(errorCode, message);
    }

    public static BadRequestException invalidClassPackageStatus(String status) {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_INVALID_CLASS_PACKAGE_STATUS;
        String message = String.format("%s. Status: %s", errorCode.getMessageCode(), status);
        return new BadRequestException(errorCode, message);
    }

    public static BadRequestException pricePerHourMustBeGreaterThanZero(double pricePerHour) {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_PRICE_PER_HOUR_MUST_BE_GREATER_THAN_ZERO;
        String message = String.format("%s. Price per hour: %s", errorCode.getMessageCode(), pricePerHour);
        return new BadRequestException(errorCode, message);
    }

    public static BadRequestException priceTrialMustBeGreaterThanZero(double priceTrial) {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_PRICE_TRIAL_MUST_BE_GREATER_THAN_ZERO;
        String message = String.format("%s. Price trial: %s", errorCode.getMessageCode(), priceTrial);
        return new BadRequestException(errorCode, message);
    }

    public static BadRequestException pricePerHourMustBeGreaterThanPriceTrial(double pricePerHour, double priceTrial) {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_PRICE_PER_HOUR_MUST_BE_GREATER_THAN_PRICE_TRIAL;
        String message = String.format("%s. Price per hour: %s, Price trial: %s", errorCode.getMessageCode(), pricePerHour, priceTrial);
        return new BadRequestException(errorCode, message);
    }

    public static BadRequestException invalidField(String fieldName, String content) {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_INVALID_FIELD;
        String message = String.format("%s. Field name: %s, Content: %s", errorCode.getMessageCode(), fieldName, content);
        return new BadRequestException(errorCode, message);
    }

    public static BadRequestException invalidWeeklyDate(String date) {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_INVALID_WEEKLY_DATE;
        String message = String.format("%s. Invalid date format. Expected: %s. Received: %s", errorCode.getMessageCode(), DateUtils.AVAILABILITY_DATE_FORMAT, date);
        return new BadRequestException(errorCode, message);
    }

    public static BadRequestException invalidRequest(HttpMessageConversionException e) {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_INVALID_REQUEST;
        String message = String.format("%s. %s", errorCode.getMessageCode(), e.getMessage());
        return new BadRequestException(errorCode, message);
    }
}


