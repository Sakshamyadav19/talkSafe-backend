package com.yourorg.talkSafe.backend.notification;

import org.springframework.stereotype.Component;

@Component
public class PushNotificationChannel implements NotificationChannel {
    @Override
    public void send(String recipient, String message) {
        // Use push notification service
        System.out.println("Sending Push to " + recipient + ": " + message);
    }
}