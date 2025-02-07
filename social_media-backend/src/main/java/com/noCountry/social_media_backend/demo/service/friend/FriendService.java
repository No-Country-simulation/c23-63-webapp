package com.noCountry.social_media_backend.demo.service.friend;

import com.noCountry.social_media_backend.demo.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;

@Service
public class FriendService {

    @Autowired
    public final FriendRepository friendRepository;

    public FriendService(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    public boolean areFriends(Integer userId, Integer friendId) {
        if (userId.equals(friendId)) {
            return false;
        }
        return friendRepository.areFriends(userId, friendId);
    }
}
