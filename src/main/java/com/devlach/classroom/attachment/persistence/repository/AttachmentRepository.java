package com.devlach.classroom.attachment.persistence.repository;

import com.devlach.classroom.entity.Attachment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttachmentRepository extends CrudRepository<Attachment, Long> {
    Optional<Attachment> findByIdAndDeletedAtIsNull(Long id);
}
