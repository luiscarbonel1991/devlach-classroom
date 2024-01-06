package com.devlach.classroom.attachment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;

import java.net.URI;

@Configuration
public class AwsS3Config {

    private static final String LOCALSTACK_ENDPOINT = "http://localhost:4566";
    private final BucketSettings bucketSettings;

    public AwsS3Config(BucketSettings bucketSettings) {
        this.bucketSettings = bucketSettings;
    }

    @Bean
    public S3AsyncClient s3AsyncClient() {
        return S3AsyncClient.crtBuilder()
                .endpointOverride(URI.create(LOCALSTACK_ENDPOINT))
                .forcePathStyle(true)
                .region(Region.of(bucketSettings.getRegion()))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(bucketSettings.getAccessKey(), bucketSettings.getSecretKey())))
                .build();
    }
}
