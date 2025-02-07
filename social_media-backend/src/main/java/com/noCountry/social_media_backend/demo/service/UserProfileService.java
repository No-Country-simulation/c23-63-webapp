package com.noCountry.social_media_backend.demo.service;

import com.noCountry.social_media_backend.demo.dto.infoUser.LinkDTO;
import com.noCountry.social_media_backend.demo.dto.infoUser.UserProfileRequestDTO;
import com.noCountry.social_media_backend.demo.dto.infoUser.UserProfileResponseDTO;
import com.noCountry.social_media_backend.demo.entity.user.User;
import com.noCountry.social_media_backend.demo.entity.user_profile.UserProfile;
import com.noCountry.social_media_backend.demo.repository.FriendRepository;
import com.noCountry.social_media_backend.demo.repository.UserProfileRepository;
import com.noCountry.social_media_backend.demo.repository.UserRepository;
import com.noCountry.social_media_backend.demo.service.friend.FriendService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;
    private final FriendService friendService;

    public UserProfileService(UserProfileRepository userProfileRepository, UserRepository userRepository,FriendService friendService) {
        this.userProfileRepository = userProfileRepository;
        this.userRepository = userRepository;
        this.friendService = friendService;
    }

    public UserProfile createUserProfile(UserProfileRequestDTO userProfileRequestDTO) {
        User user = userRepository.findById(userProfileRequestDTO.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserProfile userProfile = UserProfile.builder()
                .user(user)
                .name(userProfileRequestDTO.name())
                .jobTitle(userProfileRequestDTO.jobTitle())
                .discordLink(getLinkByType(userProfileRequestDTO.links(), "discord"))
                .linkedinLink(getLinkByType(userProfileRequestDTO.links(), "linkedin"))
                .githubLink(getLinkByType(userProfileRequestDTO.links(), "github"))
                .instagramLink(getLinkByType(userProfileRequestDTO.links(), "instagram"))
                .youtubeLink(getLinkByType(userProfileRequestDTO.links(), "youtube"))
                .personalLink(getLinkByType(userProfileRequestDTO.links(), "personal"))
                .build();

        return userProfileRepository.save(userProfile);
    }

    private String getLinkByType(List<LinkDTO> links, String type) {
        return links.stream()
                .filter(link -> link.type().equalsIgnoreCase(type))
                .map(LinkDTO::url)
                .findFirst()
                .orElse(null);  // Si no se encuentra, devuelve null
    }

    public void getUserProfile(Integer userId, Integer viewerId) {
       boolean isFriend = friendService.areFriends(userId,viewerId);

       UserProfile userProfile = userProfileRepository.findById(viewerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Profile not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        List<LinkDTO> links = new ArrayList<>();
        links.add(new LinkDTO("discord", userProfile.getDiscordLink()));
        links.add(new LinkDTO("linkedin", userProfile.getLinkedinLink()));
        links.add(new LinkDTO("github", userProfile.getGithubLink()));
        links.add(new LinkDTO("instagram", userProfile.getInstagramLink()));
        links.add(new LinkDTO("youtube", userProfile.getYoutubeLink()));
        links.add(new LinkDTO("personal", userProfile.getPersonalLink()));




    }

    public UserProfile findByUserId(Integer userId) {
        return userProfileRepository.findByUserId(userId)
                .orElse(null);
    }
}