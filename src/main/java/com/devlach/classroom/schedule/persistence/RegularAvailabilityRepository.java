package com.devlach.classroom.schedule.persistence;

import com.devlach.classroom.entity.RegularAvailability;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegularAvailabilityRepository extends ListCrudRepository<RegularAvailability, Long> {
    List<RegularAvailability> findAllByProfileIdAndDeletedAtIsNull(Long profileId);

    List<RegularAvailability> findAllByProfileIdAndDayOfWeekInAndDeletedAtIsNull(Long profileId, List<String> daysOfWeek);

    List<RegularAvailability> findAllByProfileIdAndIdInAndDeletedAtIsNull(Long profileId, List<Long> ids);
}
