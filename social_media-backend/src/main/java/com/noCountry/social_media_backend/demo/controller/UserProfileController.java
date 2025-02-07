package com.noCountry.social_media_backend.demo.controller;

import com.noCountry.social_media_backend.demo.dto.infoUser.UserProfileRequestDTO;
import com.noCountry.social_media_backend.demo.dto.infoUser.UserProfileResponseDTO;
import com.noCountry.social_media_backend.demo.entity.user_profile.UserProfile;
import com.noCountry.social_media_backend.demo.service.UserProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-profiles")
public class UserProfileController {
    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping("/update")
    public ResponseEntity<UserProfile> updateUserProfile(@RequestBody UserProfileRequestDTO userProfileRequestDTO) {
        UserProfile userProfile = userProfileService.updateUserProfile(userProfileRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userProfile);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserProfileResponseDTO> getUserProfile(
            @PathVariable Integer userId,
            @RequestParam Integer sessionId) {

        UserProfileResponseDTO response = userProfileService.getUserProfile(userId, sessionId);
        return ResponseEntity.ok(response);
    }



}
