package com.devlach.classroom.attachment.dto;

import com.devlach.classroom.api.exception.BadRequestException;
import com.devlach.classroom.entity.EntityType;
import org.springframework.web.multipart.MultipartFile;

public record CreateAttachmentDTO(
        MultipartFile file,

        String name,
        String description,
        EntityType entityType
) {
    public void validate() {
        if (file == null) {
            throw BadRequestException.requiredField("file");
        }
        if (description == null || description.isBlank()) {
            throw BadRequestException.invalidField("description", "Description is required");
        }
        if (entityType == null) {
            throw BadRequestException.requiredField("entityType");
        }
    }
}
