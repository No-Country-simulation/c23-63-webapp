package com.noCountry.social_media_backend.demo.repository;

import com.noCountry.social_media_backend.demo.entity.friend.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friend, Integer> {
}