package com.devlach.classroom.attachment.mapper;

import com.devlach.classroom.attachment.dto.AttachmentDTO;

@FunctionalInterface
public interface ToAttachmentDTO {

        AttachmentDTO toDTO();
}
