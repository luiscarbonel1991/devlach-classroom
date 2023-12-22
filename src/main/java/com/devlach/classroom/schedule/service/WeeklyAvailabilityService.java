package com.devlach.classroom.schedule.service;

import com.devlach.classroom.entity.WeeklyAvailability;
import com.devlach.classroom.schedule.dto.weekly.CreateUpdateWeeklyAvailabilityDTO;
import com.devlach.classroom.schedule.dto.weekly.CreateWeeklyAvailabilityBatchDTO;
import com.devlach.classroom.schedule.dto.weekly.UpdateWeeklyAvailabilityBatchDTO;
import com.devlach.classroom.schedule.dto.weekly.WeeklyAvailabilityDTO;
import com.devlach.classroom.schedule.mapper.ScheduleMapper;
import com.devlach.classroom.schedule.persistence.WeeklyAvailabilityRepository;
import com.devlach.classroom.users.dto.ProfileDTO;
import com.devlach.classroom.utils.DateUtils;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class WeeklyAvailabilityService {

    public static final int WEEKLY_QUANTITY_DAYS = 7;
    private final WeeklyAvailabilityRepository weeklyAvailabilityRepository;

    public WeeklyAvailabilityService(WeeklyAvailabilityRepository weeklyAvailabilityRepository) {
        this.weeklyAvailabilityRepository = weeklyAvailabilityRepository;
    }

    public List<WeeklyAvailabilityDTO> findAll(ProfileDTO profile, String startDate, String endDate) {
        var dateRange = DateUtils.parseRangeAvailabilityDate(startDate, endDate);
        if (dateRange.localDates().size() > WEEKLY_QUANTITY_DAYS) {
            throw new IllegalArgumentException("Date range must be less than 7 days");
        }
        List<WeeklyAvailability> availability = weeklyAvailabilityRepository.findAllByProfileIdAndDateBetweenAndDeletedAtIsNull(profile.id(), dateRange.getStart(), dateRange.getEnd());
        return ScheduleMapper.mapWeeklyAvailabilityDTOList(availability);
    }

    @Transactional
    public List<WeeklyAvailability> create(CreateWeeklyAvailabilityBatchDTO batchDTO, ProfileDTO profile) {
        batchDTO.validate();
        var dates = batchDTO.weeklyAvailabilities().stream()
                .map(CreateUpdateWeeklyAvailabilityDTO::date)
                .map(DateUtils::parseAvailabilityDate)
                .toList();
        var availability = weeklyAvailabilityRepository.findAllByProfileIdAndDateInAndDeletedAtIsNull(profile.id(), dates);
        return batchDTO.weeklyAvailabilities().stream()
                .map(dto -> create(dto, profile, availability))
                .toList();
    }

    public WeeklyAvailability create(CreateUpdateWeeklyAvailabilityDTO dto, ProfileDTO profile, List<WeeklyAvailability> availabilities) {
        dto.validateCreate();
        var date = DateUtils.parseAvailabilityDate(dto.date());
        var startTime = DateUtils.parseAvailabilityTime(dto.startTime());
        var endTime = DateUtils.parseAvailabilityTime(dto.endTime());
        var entityToCreate = ScheduleMapper.map(dto, profile.id()).toEntity();

        availabilities.stream()
                .filter(availability -> availability.getDate().equals(date))
                .filter(availability -> startTime.isBefore(availability.getEndTime()) && endTime.isAfter(availability.getStartTime()))
                .findFirst()
                .ifPresent(availability -> {
                    throw new IllegalArgumentException("There is already an availability for the same date and time overlap");
                });
        availabilities.add(entityToCreate);
        return weeklyAvailabilityRepository.save(entityToCreate);

    }

    public List<WeeklyAvailability> update(UpdateWeeklyAvailabilityBatchDTO batchDTO, ProfileDTO profile) {
        batchDTO.validate();
        var availabilities = weeklyAvailabilityRepository.findAllByProfileIdAndIdInAndDeletedAtIsNull(profile.id(), batchDTO.ids());
        return batchDTO.weeklyAvailabilities().stream()
                .map(dto -> update(dto, availabilities))
                .toList();
    }

    public WeeklyAvailability update(CreateUpdateWeeklyAvailabilityDTO dto, List<WeeklyAvailability> availabilities) {
        dto.validateUpdate();
        var date = DateUtils.parseAvailabilityDate(dto.date());
        var startTime = DateUtils.parseAvailabilityTime(dto.startTime());
        var endTime = DateUtils.parseAvailabilityTime(dto.endTime());
        var entityToUpdate = availabilities.stream()
                .filter(availability -> availability.getId().equals(dto.id()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Availability not found"));
        availabilities.stream()
                .filter(availability -> !availability.getId().equals(dto.id()))
                .filter(availability -> availability.getDate().equals(date))
                .filter(availability -> startTime.isBefore(availability.getEndTime()) && endTime.isAfter(availability.getStartTime()))
                .findFirst()
                .ifPresent(availability -> {
                    throw new IllegalArgumentException("There is already an availability for the same date and time overlap");
                });
        entityToUpdate.setDate(date);
        entityToUpdate.setStartTime(startTime);
        entityToUpdate.setEndTime(endTime);
        return weeklyAvailabilityRepository.save(entityToUpdate);
    }

    public void delete(Long weeklyAvailabilityId, ProfileDTO profile) {
        WeeklyAvailability availability = weeklyAvailabilityRepository.findAllByProfileIdAndIdInAndDeletedAtIsNull(profile.id(), List.of(weeklyAvailabilityId))
                .stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Availability not found"));
        availability.setDeletedAt(Instant.now());
        weeklyAvailabilityRepository.save(availability);
    }
}
