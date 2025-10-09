package com.flipkart.backend.aws;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

/**
 * AWS SQS Service for handling asynchronous order processing
 * Manages message queues for order notifications and background tasks
 */
@Service
@RequiredArgsConstructor
public class SQSService {
    
    private final SqsClient sqsClient;
    
    @Value("${aws.sqs.queue-url:}")
    private String queueUrl;
    
    public String sendMessage(String messageBody) {
        try {
            SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(messageBody)
                .build();
            
            SendMessageResponse response = sqsClient.sendMessage(sendMessageRequest);
            return response.messageId();
        } catch (Exception e) {
            throw new RuntimeException("Failed to send message to SQS", e);
        }
    }
    
    public void receiveMessages() {
        try {
            ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl(queueUrl)
                .maxNumberOfMessages(10)
                .waitTimeSeconds(20)
                .build();
            
            ReceiveMessageResponse response = sqsClient.receiveMessage(receiveMessageRequest);
            
            for (Message message : response.messages()) {
                processMessage(message);
                deleteMessage(message.receiptHandle());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to receive messages from SQS", e);
        }
    }
    
    private void processMessage(Message message) {
        // Process the message (e.g., order processing, notifications)
        System.out.println("Processing message: " + message.body());
    }
    
    private void deleteMessage(String receiptHandle) {
        try {
            DeleteMessageRequest deleteMessageRequest = DeleteMessageRequest.builder()
                .queueUrl(queueUrl)
                .receiptHandle(receiptHandle)
                .build();
            
            sqsClient.deleteMessage(deleteMessageRequest);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete message from SQS", e);
        }
    }
}
