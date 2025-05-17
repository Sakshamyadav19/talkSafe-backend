package com.yourorg.talkSafe.backend.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Factory Method for NotificationChannel instances.
 */
@Component
public class NotificationChannelFactory {

    @Autowired
    private ApplicationContext context;

    public NotificationChannel getChannel(ChannelType type) {
        switch (type) {
            case SMS:
                return context.getBean(SmsNotificationChannel.class);
            case PUSH:
                return context.getBean(PushNotificationChannel.class);
            case EMAIL:
                return context.getBean(EmailNotificationChannel.class);
            default:
                throw new IllegalArgumentException("Unknown channel type: " + type);
        }
    }
}