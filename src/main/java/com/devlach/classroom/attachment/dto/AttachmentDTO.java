package com.devlach.classroom.attachment.dto;

import com.devlach.classroom.entity.AttachmentType;
import com.devlach.classroom.entity.FileExtensionType;

public record AttachmentDTO(
        Long id,
        String name,
        String description,
        AttachmentType type,
        FileExtensionType extension,
        Long size,
        String path
) {
}
