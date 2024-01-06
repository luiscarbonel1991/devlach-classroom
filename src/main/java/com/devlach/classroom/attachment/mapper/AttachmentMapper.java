package com.devlach.classroom.attachment.mapper;

import com.devlach.classroom.attachment.dto.AttachmentDTO;
import com.devlach.classroom.entity.Attachment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AttachmentMapper {

    public ToAttachmentDTO map(Attachment attachment){
        return () -> new AttachmentDTO(
                attachment.getId(),
                attachment.getName(),
                attachment.getDescription(),
                attachment.getType(),
                attachment.getExtension(),
                attachment.getSize(),
                attachment.getPath()
        );
    }
}
