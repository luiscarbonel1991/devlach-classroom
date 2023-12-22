package com.devlach.classroom.users.persistence;

import com.devlach.classroom.entity.Profile;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends ListCrudRepository<Profile, Long> {
}
