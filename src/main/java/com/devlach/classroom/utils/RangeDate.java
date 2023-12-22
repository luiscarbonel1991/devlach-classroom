package com.devlach.classroom.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RangeDate<T extends Temporal> {

    private T start;
    private T end;

    public List<LocalDate> localDates() {
        return ((LocalDate) start).datesUntil(((LocalDate) end).plusDays(1)).toList();
    }
}
