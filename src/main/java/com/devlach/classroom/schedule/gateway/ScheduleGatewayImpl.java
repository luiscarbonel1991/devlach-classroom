package com.devlach.classroom.schedule.gateway;

import com.devlach.classroom.entity.ProfileType;
import com.devlach.classroom.schedule.dto.regular.CreateRegularAvailabilityBatchDTO;
import com.devlach.classroom.schedule.dto.regular.RegularAvailabilityDTO;
import com.devlach.classroom.schedule.dto.regular.UpdateRegularAvailabilityBatchDTO;
import com.devlach.classroom.schedule.dto.weekly.CreateWeeklyAvailabilityBatchDTO;
import com.devlach.classroom.schedule.dto.weekly.UpdateWeeklyAvailabilityBatchDTO;
import com.devlach.classroom.schedule.dto.weekly.WeeklyAvailabilityDTO;
import com.devlach.classroom.schedule.mapper.ScheduleMapper;
import com.devlach.classroom.schedule.service.RegularAvailabilityService;
import com.devlach.classroom.schedule.service.WeeklyAvailabilityService;
import com.devlach.classroom.users.dto.ProfileDTO;
import com.devlach.classroom.users.gateway.UserGateway;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduleGatewayImpl implements ScheduleGateway {

    private final UserGateway userGateway;

    private final RegularAvailabilityService regularAvailabilityService;

    private final WeeklyAvailabilityService weeklyAvailabilityService;

    public ScheduleGatewayImpl(UserGateway userGateway, RegularAvailabilityService regularAvailabilityService, WeeklyAvailabilityService weeklyAvailabilityService) {
        this.userGateway = userGateway;
        this.regularAvailabilityService = regularAvailabilityService;
        this.weeklyAvailabilityService = weeklyAvailabilityService;
    }

    @Override
    public List<RegularAvailabilityDTO> findRegularAvailability(String email) {
        ProfileDTO profile = userGateway.findProfileByEmail(email, ProfileType.TEACHER);
        return ScheduleMapper.map(regularAvailabilityService.findAllByProfileId(profile.id())).toListDTO();
    }

    @Override
    public List<RegularAvailabilityDTO> createRegularAvailability(String email, CreateRegularAvailabilityBatchDTO batchDTO) {
        ProfileDTO profile = userGateway.findProfileByEmail(email, ProfileType.TEACHER);
        return ScheduleMapper.map(regularAvailabilityService.create(batchDTO, profile)).toListDTO();
    }

    @Override
    public List<RegularAvailabilityDTO> updateRegularAvailability(String email, UpdateRegularAvailabilityBatchDTO batchDTO) {
        ProfileDTO profile = userGateway.findProfileByEmail(email, ProfileType.TEACHER);
        return ScheduleMapper
                .map(regularAvailabilityService.update(batchDTO, profile))
                .toListDTO();
    }

    @Override
    public void deleteRegularAvailability(Long regularAvailabilityId, String email) {
        ProfileDTO profile = userGateway.findProfileByEmail(email, ProfileType.TEACHER);
        regularAvailabilityService.delete(regularAvailabilityId, profile);
    }

    @Override
    public List<WeeklyAvailabilityDTO> findWeeklyAvailability(String startDate, String endDate, String email) {
        ProfileDTO profile = userGateway.findProfileByEmail(email, ProfileType.TEACHER);
        return weeklyAvailabilityService.findAll(profile, startDate, endDate);
    }

    @Override
    public List<WeeklyAvailabilityDTO> createWeeklyAvailability(String email, CreateWeeklyAvailabilityBatchDTO batchDTO) {
        ProfileDTO profile = userGateway.findProfileByEmail(email, ProfileType.TEACHER);
        return ScheduleMapper.mapWeeklyAvailabilityDTOList(weeklyAvailabilityService.create(batchDTO, profile));
    }

    @Override
    public List<WeeklyAvailabilityDTO> updateWeeklyAvailability(String email, UpdateWeeklyAvailabilityBatchDTO batchDTO) {
        ProfileDTO profile = userGateway.findProfileByEmail(email, ProfileType.TEACHER);
        return ScheduleMapper.mapWeeklyAvailabilityDTOList(weeklyAvailabilityService.update(batchDTO, profile));
    }

    @Override
    public void deleteWeeklyAvailability(Long weeklyAvailabilityId, String email) {
        ProfileDTO profile = userGateway.findProfileByEmail(email, ProfileType.TEACHER);
        weeklyAvailabilityService.delete(weeklyAvailabilityId, profile);
    }
}
