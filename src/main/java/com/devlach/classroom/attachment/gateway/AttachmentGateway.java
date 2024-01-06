package com.devlach.classroom.attachment.gateway;

import com.devlach.classroom.attachment.dto.AttachmentDTO;
import com.devlach.classroom.attachment.dto.CreateAttachmentDTO;
import com.devlach.classroom.attachment.mapper.AttachmentMapper;
import com.devlach.classroom.attachment.service.AttachmentService;
import com.devlach.classroom.users.dto.UserDTO;
import com.devlach.classroom.users.gateway.UserGateway;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AttachmentGateway {

    private final UserGateway userGateway;
    private final AttachmentService attachmentService;

    public AttachmentGateway(UserGateway userGateway, AttachmentService attachmentService) {
        this.userGateway = userGateway;
        this.attachmentService = attachmentService;
    }

    public AttachmentDTO upload(CreateAttachmentDTO dto, String owner) {
        UserDTO userDTO = userGateway.findByEmail(owner);
        return AttachmentMapper.map(attachmentService.uploadImage(dto, userDTO)).toDTO();
    }

    public Map<String, Object> download(Long id) {
        return attachmentService.download(id);
    }

    public void delete(Long id) {
        attachmentService.delete(id);
    }
}
