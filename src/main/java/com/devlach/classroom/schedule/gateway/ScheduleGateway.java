package com.devlach.classroom.schedule.gateway;

import com.devlach.classroom.schedule.dto.*;

import java.util.List;

public interface ScheduleGateway {

    List<RegularAvailabilityDTO> findRegularAvailability(String email);

    List<RegularAvailabilityDTO> createRegularAvailability(String email, CreateRegularAvailabilityBatchDTO dto);

    List<RegularAvailabilityDTO> updateRegularAvailability(String email, UpdateRegularAvailabilityBatchDTO batchDTO);

    void deleteRegularAvailability(Long id, String email);

    List<WeeklyAvailabilityDTO> findWeeklyAvailability(String startDate, String endDate, String email);

    List<WeeklyAvailabilityDTO> createWeeklyAvailability(String email, CreateWeeklyAvailabilityBatchDTO batchDTO);
}
