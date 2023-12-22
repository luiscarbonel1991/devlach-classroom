package com.devlach.classroom.api.exception;

import com.devlach.classroom.schedule.enums.DayOfWeek;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;
import java.time.LocalTime;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends AppException{

    public ConflictException(ErrorCode errorCode, String message) {
        super(errorCode, message);
        super.errors.add(message);
    }

    public static ConflictException weeklyAvailabilityOverlap(LocalDate date, LocalTime startTime, LocalTime endTime) {
        ErrorCode errorCode = ErrorCode.CONFLICT_WEEKLY_AVAILABILITY_OVERLAP;
        String message = String.format("%s. Date: %s, Start time: %s, End time: %s", errorCode.getMessageCode(), date, startTime.toString(), endTime.toString());
        return new ConflictException(errorCode, message);
    }

    public static ConflictException regularAvailabilityOverlap(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        ErrorCode errorCode = ErrorCode.CONFLICT_REGULAR_AVAILABILITY_OVERLAP;
        String message = String.format("%s. Day of week: %s, Start time: %s, End time: %s", errorCode.getMessageCode(), dayOfWeek.toString(), startTime.toString(), endTime.toString());
        return new ConflictException(errorCode, message);
    }


    public static ConflictException userEmailAlreadyExists(String email) {
        ErrorCode errorCode = ErrorCode.CONFLICT_USER_EMAIL_ALREADY_EXISTS;
        String message = String.format("%s. Email: %s", errorCode.getMessageCode(), email);
        return new ConflictException(errorCode, message);
    }
}
