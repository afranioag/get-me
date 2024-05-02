package com.aag.getme.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {

    @Value("${aws.s3.access-key-id}")
    private String accessKeyId;

    @Value("${aws.s3.secret-access-key}")
    private String secretAccessKey;


    @Bean
    public AmazonS3 amazonS3() {
        return AmazonS3Client.builder()
                .withCredentials(new AWSStaticCredentialsProvider( new BasicAWSCredentials(secretAccessKey, accessKeyId)))
                .withRegion("sa-east-1")
                .build();
    }
}
