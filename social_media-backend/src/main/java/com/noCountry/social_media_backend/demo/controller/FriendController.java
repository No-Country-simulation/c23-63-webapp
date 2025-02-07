package com.noCountry.social_media_backend.demo.controller;

import com.noCountry.social_media_backend.demo.service.friend.FriendService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friends")
public class FriendController {

    private final FriendService friendService;

    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addFriend(@RequestParam Integer userId, @RequestParam Integer sessionId) {
        friendService.addFriend(userId, sessionId);
        return ResponseEntity.ok("Friend request sent successfully.");
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeFriend(@RequestParam Integer userId, @RequestParam Integer sessionId) {
        friendService.removeFriend(userId, sessionId);
        return ResponseEntity.ok("Friendship removed successfully.");
    }




}
