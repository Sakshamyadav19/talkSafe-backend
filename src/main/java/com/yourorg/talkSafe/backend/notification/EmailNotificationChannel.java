package com.yourorg.talkSafe.backend.notification;

import org.springframework.stereotype.Component;

@Component
public class EmailNotificationChannel implements NotificationChannel {
    @Override
    public void send(String recipient, String message) {
        // Use email SMTP or service API
        System.out.println("Sending Email to " + recipient + ": " + message);
    }
}