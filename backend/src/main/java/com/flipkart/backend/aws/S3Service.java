package com.flipkart.backend.aws;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import java.io.InputStream;

/**
 * AWS S3 Service for managing product images and other files
 * Handles upload, download, and deletion of files in S3 buckets
 */
@Service
@RequiredArgsConstructor
public class S3Service {
    
    private final S3Client s3Client;
    
    @Value("${aws.s3.bucket-name:flipkart-images}")
    private String bucketName;
    
    public String uploadFile(String key, InputStream inputStream, long contentLength) {
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
            
            s3Client.putObject(putObjectRequest, 
                RequestBody.fromInputStream(inputStream, contentLength));
            
            return getFileUrl(key);
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file to S3", e);
        }
    }
    
    public InputStream downloadFile(String key) {
        try {
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
            
            return s3Client.getObject(getObjectRequest);
        } catch (Exception e) {
            throw new RuntimeException("Failed to download file from S3", e);
        }
    }
    
    public void deleteFile(String key) {
        try {
            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
            
            s3Client.deleteObject(deleteObjectRequest);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete file from S3", e);
        }
    }
    
    public String getFileUrl(String key) {
        return String.format("https://%s.s3.amazonaws.com/%s", bucketName, key);
    }
}
