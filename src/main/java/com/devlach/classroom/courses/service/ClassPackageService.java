package com.devlach.classroom.courses.service;

import com.devlach.classroom.courses.dto.CreateUpdateClassPackageDTO;
import com.devlach.classroom.courses.mapper.ClassPackageMapper;
import com.devlach.classroom.courses.persistence.repository.ClassPackageRepository;
import com.devlach.classroom.entity.ClassPackage;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ClassPackageService {

    private final ClassPackageRepository classPackageRepository;

    public ClassPackageService(ClassPackageRepository classPackageRepository) {
        this.classPackageRepository = classPackageRepository;
    }

    @Transactional
    public ClassPackage create(CreateUpdateClassPackageDTO dto, Long studentId, Long coursePricingId) {
        return classPackageRepository.save(ClassPackageMapper.map(dto, studentId, coursePricingId).toEntity());
    }
}
