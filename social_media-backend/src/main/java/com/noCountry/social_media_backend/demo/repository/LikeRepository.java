package com.noCountry.social_media_backend.demo.repository;

import com.noCountry.social_media_backend.demo.entity.like.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Integer> {
}
