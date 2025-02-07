package com.noCountry.social_media_backend.demo.repository;

import com.noCountry.social_media_backend.demo.entity.friend.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer> {
    @Query("SELECT COUNT(f) > 0 FROM Friend f WHERE " +
            "(f.user.id = :userId AND f.friend.id = :friendId OR " +
            "f.user.id = :friendId AND f.friend.id = :userId) " +
            "AND f.status = 'ACCEPTED'")
    boolean areFriends(@Param("userId") Integer userId, @Param("friendId") Integer friendId);

    @Query("SELECT COUNT(f) FROM Friend f WHERE f.user.id = :userId AND f.status = 'ACCEPTED'")
    int countFriends(@Param("userId") Integer userId);
}