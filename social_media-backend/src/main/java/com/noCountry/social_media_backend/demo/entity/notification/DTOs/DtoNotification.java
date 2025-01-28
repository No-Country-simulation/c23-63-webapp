package com.noCountry.social_media_backend.demo.entity.notification.DTOs;

import com.noCountry.social_media_backend.demo.entity.notification.Notification;
import com.noCountry.social_media_backend.demo.entity.user.User;

import java.time.LocalDateTime;

public record DtoNotification(
        Integer id,
        User user,
        String message,
        Boolean isRead,
        LocalDateTime createdAt
) {

    public DtoNotification(Notification notification) {
        this(notification.getId(),notification.getUser(),notification.getMessage(),notification.getIsRead(),notification.getCreatedAt());
    }
}
