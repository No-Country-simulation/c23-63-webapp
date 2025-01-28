package com.noCountry.social_media_backend.demo.repository;

import com.noCountry.social_media_backend.demo.entity.notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
