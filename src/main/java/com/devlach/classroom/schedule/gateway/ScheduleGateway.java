package com.devlach.classroom.schedule.gateway;

import com.devlach.classroom.schedule.dto.regular.CreateRegularAvailabilityBatchDTO;
import com.devlach.classroom.schedule.dto.regular.RegularAvailabilityDTO;
import com.devlach.classroom.schedule.dto.regular.UpdateRegularAvailabilityBatchDTO;
import com.devlach.classroom.schedule.dto.weekly.CreateWeeklyAvailabilityBatchDTO;
import com.devlach.classroom.schedule.dto.weekly.UpdateWeeklyAvailabilityBatchDTO;
import com.devlach.classroom.schedule.dto.weekly.WeeklyAvailabilityDTO;

import java.util.List;

public interface ScheduleGateway {

    List<RegularAvailabilityDTO> findRegularAvailability(String email);

    List<RegularAvailabilityDTO> createRegularAvailability(String email, CreateRegularAvailabilityBatchDTO dto);

    List<RegularAvailabilityDTO> updateRegularAvailability(String email, UpdateRegularAvailabilityBatchDTO batchDTO);

    void deleteRegularAvailability(Long regularAvailabilityId, String email);

    List<WeeklyAvailabilityDTO> findWeeklyAvailability(String startDate, String endDate, String email);

    List<WeeklyAvailabilityDTO> createWeeklyAvailability(String email, CreateWeeklyAvailabilityBatchDTO batchDTO);

    List<WeeklyAvailabilityDTO> updateWeeklyAvailability(String email, UpdateWeeklyAvailabilityBatchDTO batchDTO);

    void deleteWeeklyAvailability(Long weeklyAvailabilityId, String email);
}
