package com.devlach.classroom.schedule.persistence;

import com.devlach.classroom.entity.WeeklyAvailability;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WeeklyAvailabilityRepository extends ListCrudRepository<WeeklyAvailability, Long> {

    List<WeeklyAvailability> findAllByProfileIdAndIdInAndDeletedAtIsNull(Long profileId, List<Long> ids);
    List<WeeklyAvailability> findAllByProfileIdAndDateInAndDeletedAtIsNull(Long profileId, List<LocalDate> dates);
    List<WeeklyAvailability> findAllByProfileIdAndDateBetweenAndDeletedAtIsNull(Long profileId, LocalDate startDate, LocalDate endDate);
}
