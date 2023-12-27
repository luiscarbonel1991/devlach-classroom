package com.devlach.classroom.courses.persistence.repository;

import com.devlach.classroom.entity.ClassPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassPackageRepository extends JpaRepository<ClassPackage, Long> {
}
