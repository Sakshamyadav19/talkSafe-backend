package com.yourorg.talkSafe.backend.notification;

public interface NotificationChannel {
    void send(String recipient, String message);
}
