package com.devlach.classroom.courses.persistence.repository;

import com.devlach.classroom.entity.CoursePricing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursePricingRepository extends JpaRepository<CoursePricing, Long> {

}
