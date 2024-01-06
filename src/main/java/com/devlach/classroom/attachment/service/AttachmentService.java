package com.devlach.classroom.attachment.service;

import com.devlach.classroom.api.exception.BadRequestException;
import com.devlach.classroom.api.exception.NotFoundException;
import com.devlach.classroom.attachment.config.BucketSettings;
import com.devlach.classroom.attachment.dto.CreateAttachmentDTO;
import com.devlach.classroom.attachment.persistence.repository.AttachmentRepository;
import com.devlach.classroom.entity.Attachment;
import com.devlach.classroom.entity.AttachmentType;
import com.devlach.classroom.entity.FileExtensionType;
import com.devlach.classroom.users.dto.UserDTO;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3AsyncClient;

import java.io.File;
import java.nio.file.Files;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class AttachmentService {

    private static final long MAX_IMAGE_SIZE = 5L * 1024 * 1024;
    private static final List<FileExtensionType> IMAGED_TYPES = FileExtensionType.imageTypes();
    private final BucketSettings bucketSettings;

    private final AwsS3Service awsS3Service;

    private final S3AsyncClient s3AsyncClient;

    private final AttachmentRepository attachmentRepository;

    public AttachmentService(BucketSettings bucketSettings, AwsS3Service awsS3Service, S3AsyncClient s3AsyncClient, AttachmentRepository attachmentRepository) {
        this.bucketSettings = bucketSettings;
        this.awsS3Service = awsS3Service;
        this.s3AsyncClient = s3AsyncClient;
        this.attachmentRepository = attachmentRepository;
    }

    @SneakyThrows()
    public Attachment uploadImage(CreateAttachmentDTO attachmentDTO, UserDTO userDTO) {
        attachmentDTO.validate();
        MultipartFile multipartFile = attachmentDTO.file();
        validateImage(multipartFile);
        var extension = getFileExtension(multipartFile.getOriginalFilename());
        var name = attachmentDTO.name() == null ? multipartFile.getOriginalFilename() : attachmentDTO.name();
        var attachmentToSave = Attachment.builder()
                .name(multipartFile.getOriginalFilename())
                .description(attachmentDTO.description())
                .extension(FileExtensionType.of(extension))
                .name(name)
                .type(AttachmentType.IMAGE)
                .owner(userDTO.id())
                .size(multipartFile.getSize());

        String fileName = String.join(".", UUID.randomUUID().toString(), extension);
        String attachmentTypePartition = String.format("%s=%s", "attachment_type", AttachmentType.IMAGE.name().toLowerCase());
        String datePartition = String.format("%s=%s", "date", LocalDate.now());
        String key = String.join("/", attachmentDTO.entityType().name().toLowerCase(), attachmentTypePartition, datePartition, fileName);
        var path  = Files.createTempFile(null, UUID.randomUUID().toString());
        multipartFile.transferTo(path);
        awsS3Service.uploadAwait(s3AsyncClient, bucketSettings.getName(), key, path);
        attachmentToSave.path(key);
        return attachmentRepository.save(attachmentToSave.build());
    }


    public Map<String, Object> download(Long id) {
        Attachment attachment = attachmentRepository.findByIdAndDeletedAtIsNull(id).orElseThrow(() -> NotFoundException.attachmentNotFound(id));
        var content = awsS3Service.downloadAwait(s3AsyncClient, bucketSettings.getName(), attachment.getPath());
        return Map.of(
                "name", attachment.getName(),
                "content", content,
                "extension", attachment.getExtension().name().toLowerCase()
        );
    }


    private static boolean isImageExtensionValid(String filename) {
        String extension = getFileExtension(filename);
        return extension != null && IMAGED_TYPES.stream().anyMatch(e -> e.name().equalsIgnoreCase(extension));
    }

    private static String getFileExtension(String filename) {
        if (filename != null && filename.lastIndexOf(".") != -1 && filename.lastIndexOf(".") != 0) {
            return filename.substring(filename.lastIndexOf(".") + 1);
        }
        return null;
    }

    private static void validateImage(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw BadRequestException.invalidImageByEmptyOrNull();
        }

        String filename = file.getOriginalFilename();
        if (!isImageExtensionValid(filename)) {
            String extension = getFileExtension(filename);
            throw BadRequestException.invalidImageByExtension(extension, IMAGED_TYPES);
        }

        long fileSize = file.getSize();
        if (fileSize > MAX_IMAGE_SIZE) {
            throw BadRequestException.invalidImageBySize(fileSize, MAX_IMAGE_SIZE);
        }
    }

    public void delete(Long id) {
        Attachment attachment = attachmentRepository.findByIdAndDeletedAtIsNull(id).orElseThrow(() -> NotFoundException.attachmentNotFound(id));
        awsS3Service.deleteAwait(s3AsyncClient, bucketSettings.getName(), attachment.getPath());
        attachment.setDeletedAt(Instant.now());
        attachmentRepository.save(attachment);
    }
}
