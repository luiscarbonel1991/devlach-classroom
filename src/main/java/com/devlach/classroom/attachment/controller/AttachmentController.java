package com.devlach.classroom.attachment.controller;

import com.devlach.classroom.attachment.dto.AttachmentDTO;
import com.devlach.classroom.attachment.dto.CreateAttachmentDTO;
import com.devlach.classroom.attachment.gateway.AttachmentGateway;
import com.devlach.classroom.entity.EntityType;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/attachments")
public class AttachmentController {

    private final AttachmentGateway attachmentGateway;

    public AttachmentController(AttachmentGateway attachmentGateway) {
        this.attachmentGateway = attachmentGateway;
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<AttachmentDTO> upload(@RequestParam("file") MultipartFile file,
                                                @RequestParam(value = "name", required = false) String name,
                                                @RequestParam("description") String description,
                                                @RequestParam("entityType") String entityType,
                                                @RequestParam String owner) {

        return ResponseEntity.ok(attachmentGateway.upload(new CreateAttachmentDTO(file, name, description, EntityType.of(entityType)), owner));
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public ResponseEntity<byte[]> download(@PathVariable("id") Long id) {
        var metadata = attachmentGateway.download(id);
        var bytes = (byte[]) metadata.get("content");
        var filename = (String) metadata.get("name");
        var extension = (String) metadata.get("extension");
        var fileNameWithExtension = String.join(".", filename, extension);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.builder("attachment").filename(fileNameWithExtension).build().toString())
                .contentType(MediaType.parseMediaType("image/" + extension))
                .body(bytes);
    }

}
