package com.noCountry.social_media_backend.demo.repository;

import com.noCountry.social_media_backend.demo.entity.user_profile.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile,Integer> {

    Optional<UserProfile> findByUserId(Integer userId);
}
