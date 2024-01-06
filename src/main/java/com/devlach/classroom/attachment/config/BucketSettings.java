package com.devlach.classroom.attachment.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "aws.s3.buckets.classroom")
@Component
@Getter
@Setter
public class BucketSettings {
    private String name;
    private String region;
    private String accessKey;
    private String secretKey;
}
