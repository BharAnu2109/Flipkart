package com.flipkart.backend.aws;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.*;

/**
 * AWS SNS Service for sending notifications
 * Handles email/SMS notifications for order updates and user alerts
 */
@Service
@RequiredArgsConstructor
public class SNSService {
    
    private final SnsClient snsClient;
    
    @Value("${aws.sns.topic-arn:}")
    private String topicArn;
    
    public String publishMessage(String subject, String message) {
        try {
            PublishRequest publishRequest = PublishRequest.builder()
                .topicArn(topicArn)
                .subject(subject)
                .message(message)
                .build();
            
            PublishResponse response = snsClient.publish(publishRequest);
            return response.messageId();
        } catch (Exception e) {
            throw new RuntimeException("Failed to publish message to SNS", e);
        }
    }
    
    public void sendOrderNotification(String orderNumber, String status) {
        String subject = "Order Update - " + orderNumber;
        String message = String.format(
            "Your order %s has been %s", 
            orderNumber, 
            status.toLowerCase()
        );
        publishMessage(subject, message);
    }
    
    public void sendWelcomeNotification(String email) {
        String subject = "Welcome to Flipkart!";
        String message = "Thank you for registering with Flipkart. " +
                        "Start shopping now and enjoy great deals!";
        publishMessage(subject, message);
    }
}
