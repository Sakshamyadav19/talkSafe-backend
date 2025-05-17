package com.yourorg.talkSafe.backend.notification;

import org.springframework.stereotype.Service;

@Service
public class AlertService {

    private final NotificationChannelFactory factory;

    public AlertService(NotificationChannelFactory factory) {
        this.factory = factory;
    }

    public void notifyUser(String userId, String message) {
        // Example: choose channel based on user preference
        ChannelType preferred = ChannelType.SMS;
        NotificationChannel channel = factory.getChannel(preferred);
        channel.send(userId, message);
    }
}