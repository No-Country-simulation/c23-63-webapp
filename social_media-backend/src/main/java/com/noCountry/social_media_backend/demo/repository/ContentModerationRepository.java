package com.noCountry.social_media_backend.demo.repository;

import com.noCountry.social_media_backend.demo.entity.content_moderation.ContentModeration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentModerationRepository extends JpaRepository<ContentModeration,Integer> {
}
