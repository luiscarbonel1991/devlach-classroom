package com.devlach.classroom.attachment.service;

import com.devlach.classroom.api.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.DeleteObjectResponse;
import software.amazon.awssdk.transfer.s3.S3TransferManager;
import software.amazon.awssdk.transfer.s3.model.*;
import software.amazon.awssdk.transfer.s3.progress.LoggingTransferListener;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

@Service
@Slf4j
public class AwsS3Service {

    public String uploadAwait(S3AsyncClient s3AsyncClient, String bucket, String key, Path filePath){
        return uploadFile(s3AsyncClient, bucket, key, filePath).join().response().eTag();
    }

    public byte[] downloadAwait(S3AsyncClient s3AsyncClient, String bucket, String key) {
        try {
            Path filePath = Files.createTempFile(null, UUID.randomUUID().toString());
            downloadFile(s3AsyncClient, bucket, key, filePath).join();
            return Files.readAllBytes(filePath);
        } catch (Exception e) {
            throw BadRequestException.downloadFile(e);
        }
    }

    public void deleteAwait(S3AsyncClient s3AsyncClient, String bucket, String key) {
        deleteFile(s3AsyncClient, bucket, key).join();
    }

    public CompletableFuture<CompletedFileUpload> uploadFile(S3AsyncClient s3AsyncClient, String bucket, String key, Path filePath) {
        log.info("Uploading file {} to bucket {} with key {}", filePath, bucket, key);
        UploadFileRequest uploadFileRequest = UploadFileRequest.builder()
                .putObjectRequest(req -> req.bucket(bucket).key(key))
                .addTransferListener(LoggingTransferListener.create())
                .source(filePath)
                .build();
        FileUpload fileUpload = buildS3Transfermanager(s3AsyncClient).uploadFile(uploadFileRequest);
        return fileUpload.completionFuture().whenCompleteAsync(getErrorOccurredWhileUploadingFile());
    }

    public CompletableFuture<CompletedFileDownload> downloadFile(S3AsyncClient s3AsyncClient, String bucket, String key, Path filePath) {
        log.info("Downloading file from bucket {} with key {}", bucket, key);
        DownloadFileRequest fileRequest = DownloadFileRequest.builder()
                .getObjectRequest(req -> req.bucket(bucket).key(key))
                .addTransferListener(LoggingTransferListener.create())
                .destination(filePath)
                .build();
        return buildS3Transfermanager(s3AsyncClient).downloadFile(fileRequest).completionFuture().whenCompleteAsync(getErrorOccurredWhileDownloadingFile(key));
    }

    public CompletableFuture<DeleteObjectResponse> deleteFile(S3AsyncClient s3AsyncClient, String bucket, String key) {
        log.info("Deleting file from bucket {} with key {}", bucket, key);
        return s3AsyncClient.deleteObject(req -> req.bucket(bucket).key(key)).whenCompleteAsync(getErrorOccurredWhileDeletingFile());
    }

    private S3TransferManager buildS3Transfermanager(S3AsyncClient s3AsyncClient) {
        return S3TransferManager.builder()
                .s3Client(s3AsyncClient)
                .build();
    }

    private static BiConsumer<CompletedFileUpload, Throwable> getErrorOccurredWhileUploadingFile() {
        return (resp, err) -> {
            if (resp != null) {
                log.info(String.format("File uploaded successfully with ETag %s", resp.response().eTag()));
            } else {
                log.error("Error occurred while uploading file", err);
                throw BadRequestException.uploadFile(err);
            }
        };
    }

    private static BiConsumer<CompletedFileDownload, Throwable> getErrorOccurredWhileDownloadingFile(String s3Key) {
        return (resp, err) -> {
            if (resp != null) {
                log.info(String.format("File %s downloaded successfully", s3Key));
            } else {
                log.error("Error occurred while downloading file", err);
                throw BadRequestException.downloadFile(err);
            }
        };
    }

    private static BiConsumer<DeleteObjectResponse, Throwable> getErrorOccurredWhileDeletingFile() {
        return (resp, err) -> {
            if (err != null) {
                log.error("Error occurred while deleting file", err);
                throw BadRequestException.deleteFile(err);
            }
        };
    }

}
