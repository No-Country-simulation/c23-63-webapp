package com.noCountry.social_media_backend.demo.service.friend;

import com.noCountry.social_media_backend.demo.entity.friend.Friend;
import com.noCountry.social_media_backend.demo.entity.user.User;
import com.noCountry.social_media_backend.demo.repository.FriendRepository;
import com.noCountry.social_media_backend.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.Optional;

@Service
public class FriendService {
    @Autowired
    private final FriendRepository friendRepository;
    @Autowired
    private final UserRepository userRepository;

    public FriendService(FriendRepository friendRepository, UserRepository userRepository) {
        this.friendRepository = friendRepository;
        this.userRepository = userRepository;
    }

    public boolean areFriends(Integer userId, Integer friendId) {
        if (userId.equals(friendId)) {
            return false;
        }
        return friendRepository.areFriends(userId, friendId);
    }

    public int countFriends(Integer userId) {
        return friendRepository.countFriends(userId);
    }

    public void addFriend(Integer userId, Integer sessionId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        User sessionUser = userRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session user not found"));

        boolean alreadyFriends = friendRepository.existsByUserAndFriend(user, sessionUser);
        if (alreadyFriends) {
            throw new RuntimeException("Users are already friends.");
        }

        Friend friendRelation = Friend.builder()
                .user(user)
                .friend(sessionUser)
                .status("ACCEPTED")
                .build();

        friendRepository.save(friendRelation);

    }

    public void removeFriend(Integer userId, Integer sessionId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        User sessionUser = userRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session user not found"));

        // Buscar la relación de amistad en ambas direcciones
        Optional<Friend> friendship = friendRepository.findByUserAndFriend(user, sessionUser);
        Optional<Friend> reverseFriendship = friendRepository.findByUserAndFriend(sessionUser, user);

        if (friendship.isPresent()) {
            friendRepository.delete(friendship.get());
        }

        if (reverseFriendship.isPresent()) {
            friendRepository.delete(reverseFriendship.get());
        }

        if (friendship.isEmpty() && reverseFriendship.isEmpty()) {
            throw new RuntimeException("Friendship does not exist.");
        }
    }
}
