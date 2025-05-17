package com.yourorg.talkSafe.backend.notification;

import org.springframework.stereotype.Component;

@Component
public class SmsNotificationChannel implements NotificationChannel {
    @Override
    public void send(String recipient, String message) {
        // Integrate with SMS provider SDK
        System.out.println("Sending SMS to " + recipient + ": " + message);
    }
}