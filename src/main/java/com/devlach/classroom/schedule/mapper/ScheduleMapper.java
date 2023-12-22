package com.devlach.classroom.schedule.mapper;

import com.devlach.classroom.entity.Profile;
import com.devlach.classroom.entity.RegularAvailability;
import com.devlach.classroom.entity.WeeklyAvailability;
import com.devlach.classroom.schedule.dto.CreateUpdateRegularAvailabilityDTO;
import com.devlach.classroom.schedule.dto.CreateUpdateWeeklyAvailabilityDTO;
import com.devlach.classroom.schedule.dto.RegularAvailabilityDTO;
import com.devlach.classroom.schedule.dto.WeeklyAvailabilityDTO;
import com.devlach.classroom.schedule.enums.DayOfWeek;
import com.devlach.classroom.utils.DateUtils;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ScheduleMapper {



    public ToRegularAvailabilityEntity map(CreateUpdateRegularAvailabilityDTO createRegularAvailabilityDTO, Long profileId) {
        return () -> {
            var regularAvailability = new RegularAvailability();
            var profile = new Profile();
            profile.setId(profileId);
            regularAvailability.setId(createRegularAvailabilityDTO.id());
            regularAvailability.setProfile(profile);
            regularAvailability.setDayOfWeek(createRegularAvailabilityDTO.getDayOfWeek().dayOfWeek());
            regularAvailability.setStartTime(createRegularAvailabilityDTO.getStartTime());
            regularAvailability.setEndTime(createRegularAvailabilityDTO.getEndTime());
            return regularAvailability;
        };
    }

    public ToRegularAvailabilityDTO map(RegularAvailability regularAvailability) {
        var dayOfWeek = DayOfWeek.of(regularAvailability.getDayOfWeek());
        return () -> new RegularAvailabilityDTO(
                regularAvailability.getId(),
                dayOfWeek.dayOfWeek(),
                dayOfWeek.dayOfWeekNumber(),
                regularAvailability.getStartTime().toString(),
                regularAvailability.getEndTime().toString()
        );
    }

    public ToRegularAvailabilityListDTO map(List<RegularAvailability> list){
        return () -> list.stream().map(regularAvailability -> map(regularAvailability).toDTO()).toList();
    }

    public ToWeeklyAvailabilityDTO map(WeeklyAvailability weeklyAvailability) {
        return () -> new WeeklyAvailabilityDTO(
                weeklyAvailability.getId(),
                weeklyAvailability.getDate(),
                weeklyAvailability.getStartTime(),
                weeklyAvailability.getEndTime()
        );
    }

    public ToWeeklyAvailabilityEntity map(CreateUpdateWeeklyAvailabilityDTO dto, Long profileId) {
        return () -> {
            var weeklyAvailability = new WeeklyAvailability();
            var profile = new Profile();
            profile.setId(profileId);
            weeklyAvailability.setId(dto.id());
            weeklyAvailability.setProfile(profile);
            weeklyAvailability.setDate(DateUtils.parseAvailabilityDate(dto.date()));
            weeklyAvailability.setStartTime(DateUtils.parseAvailabilityTime(dto.startTime()));
            weeklyAvailability.setEndTime(DateUtils.parseAvailabilityTime(dto.endTime()));
            return weeklyAvailability;
        };
    }

    public List<WeeklyAvailabilityDTO> mapWeeklyAvailabilityDTOList(List<WeeklyAvailability> list){
        return list.stream().map(weeklyAvailability -> map(weeklyAvailability).toDTO()).toList();
    }
}
