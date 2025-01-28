package com.noCountry.social_media_backend.demo.repository;

import com.noCountry.social_media_backend.demo.entity.media.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Integer> {
}
