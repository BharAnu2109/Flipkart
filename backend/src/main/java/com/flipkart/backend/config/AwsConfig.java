package com.flipkart.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
public class AwsConfig {
    
    @Value("${aws.region:us-east-1}")
    private String region;
    
    @Value("${aws.access-key-id:}")
    private String accessKeyId;
    
    @Value("${aws.secret-access-key:}")
    private String secretAccessKey;
    
    @Bean
    public S3Client s3Client() {
        if (accessKeyId.isEmpty() || secretAccessKey.isEmpty()) {
            return S3Client.builder()
                .region(Region.of(region))
                .build();
        }
        
        return S3Client.builder()
            .region(Region.of(region))
            .credentialsProvider(StaticCredentialsProvider.create(
                AwsBasicCredentials.create(accessKeyId, secretAccessKey)))
            .build();
    }
    
    @Bean
    public SqsClient sqsClient() {
        if (accessKeyId.isEmpty() || secretAccessKey.isEmpty()) {
            return SqsClient.builder()
                .region(Region.of(region))
                .build();
        }
        
        return SqsClient.builder()
            .region(Region.of(region))
            .credentialsProvider(StaticCredentialsProvider.create(
                AwsBasicCredentials.create(accessKeyId, secretAccessKey)))
            .build();
    }
    
    @Bean
    public SnsClient snsClient() {
        if (accessKeyId.isEmpty() || secretAccessKey.isEmpty()) {
            return SnsClient.builder()
                .region(Region.of(region))
                .build();
        }
        
        return SnsClient.builder()
            .region(Region.of(region))
            .credentialsProvider(StaticCredentialsProvider.create(
                AwsBasicCredentials.create(accessKeyId, secretAccessKey)))
            .build();
    }
}
