package com.devlach.classroom.schedule.service;

import com.devlach.classroom.entity.RegularAvailability;
import com.devlach.classroom.schedule.dto.regular.CreateRegularAvailabilityBatchDTO;
import com.devlach.classroom.schedule.dto.regular.CreateUpdateRegularAvailabilityDTO;
import com.devlach.classroom.schedule.dto.regular.UpdateRegularAvailabilityBatchDTO;
import com.devlach.classroom.schedule.enums.DayOfWeek;
import com.devlach.classroom.schedule.mapper.ScheduleMapper;
import com.devlach.classroom.schedule.persistence.RegularAvailabilityRepository;
import com.devlach.classroom.users.dto.ProfileDTO;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
public class RegularAvailabilityService {

    private final RegularAvailabilityRepository regularAvailabilityRepository;

    public RegularAvailabilityService(RegularAvailabilityRepository regularAvailabilityRepository) {
        this.regularAvailabilityRepository = regularAvailabilityRepository;
    }

    public List<RegularAvailability> findAllByProfileId(Long profileId) {
        return regularAvailabilityRepository.findAllByProfileIdAndDeletedAtIsNull(profileId);
    }

    public List<RegularAvailability> findAllByProfileIdAndIdIn(Long profileId, List<Long> ids) {
        return regularAvailabilityRepository.findAllByProfileIdAndIdInAndDeletedAtIsNull(profileId, ids);
    }

    @Transactional
    public List<RegularAvailability> create(CreateRegularAvailabilityBatchDTO batchDTO, ProfileDTO profile) {
        List<RegularAvailability> availability = findAllByProfileId(profile.id());
        return batchDTO.regularAvailabilities().stream()
                .map(dto -> create(dto, profile, availability))
                .toList();
    }

    @Transactional
    public List<RegularAvailability> update(UpdateRegularAvailabilityBatchDTO batchDTO, ProfileDTO profile) {
        batchDTO.validate();
        List<Long> ids = batchDTO.regularAvailabilities().stream()
                .map(CreateUpdateRegularAvailabilityDTO::id)
                .filter(Objects::nonNull)
                .toList();
        List<RegularAvailability> availability = findAllByProfileIdAndIdIn(profile.id(), ids);
        return batchDTO.regularAvailabilities().stream()
                .map(dto -> update(dto, profile, availability))
                .toList();
    }

    public RegularAvailability create(CreateUpdateRegularAvailabilityDTO dto, ProfileDTO profile, List<RegularAvailability> availability) {
        dto.validateCreate();

        var start = dto.getStartTime();
        var end = dto.getEndTime();
        var optionalAvailability = availability.stream()
                .filter(a -> DayOfWeek.of(a.getDayOfWeek()).equals(dto.getDayOfWeek()))
                .filter(a -> a.getStartTime().isBefore(end) && a.getEndTime().isAfter(start))
                .findFirst();
        if (optionalAvailability.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "There is already an availability for this time");
        }
        RegularAvailability entity = ScheduleMapper.map(dto, profile.id()).toEntity();
        availability.add(entity);
        return regularAvailabilityRepository.save(entity);
    }

    public RegularAvailability update(CreateUpdateRegularAvailabilityDTO dto, ProfileDTO profile, List<RegularAvailability> availability) {
        dto.validateUpdate();

        var start = dto.getStartTime();
        var end = dto.getEndTime();
        var availabilityToUpdate = availability.stream()
                .filter(a -> a.getId().equals(dto.id()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Availability not found"));
        var optionalAvailability = availability.stream()
                .filter(a -> !a.getId().equals(availabilityToUpdate.getId()))
                .filter(a -> DayOfWeek.of(a.getDayOfWeek()).equals(dto.getDayOfWeek()))
                .filter(a -> a.getStartTime().isBefore(end) && a.getEndTime().isAfter(start))
                .findFirst();
        if (optionalAvailability.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "There is already an availability for this time");
        }
        RegularAvailability entity = ScheduleMapper.map(dto, profile.id()).toEntity();
        availability.stream()
                .filter(a -> a.getId().equals(dto.id()))
                .findFirst()
                .ifPresent(a -> {
                    a.setDayOfWeek(entity.getDayOfWeek());
                    a.setStartTime(entity.getStartTime());
                    a.setEndTime(entity.getEndTime());
                });
        return regularAvailabilityRepository.save(entity);
    }

    public void delete(Long id, ProfileDTO profile) {
        RegularAvailability availability = regularAvailabilityRepository.findAllByProfileIdAndIdInAndDeletedAtIsNull(profile.id(), List.of(id))
                .stream()
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Availability not found"));
        availability.setDeletedAt(Instant.now());
        regularAvailabilityRepository.save(availability);
    }
}
