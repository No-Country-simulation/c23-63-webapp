package com.noCountry.social_media_backend.demo.service;

import com.noCountry.social_media_backend.demo.dto.infoUser.LinkDTO;
import com.noCountry.social_media_backend.demo.dto.infoUser.UserProfileRequestDTO;
import com.noCountry.social_media_backend.demo.entity.user.User;
import com.noCountry.social_media_backend.demo.entity.user_profile.UserProfile;
import com.noCountry.social_media_backend.demo.repository.UserProfileRepository;
import com.noCountry.social_media_backend.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;

    public UserProfileService(UserProfileRepository userProfileRepository, UserRepository userRepository) {
        this.userProfileRepository = userProfileRepository;
        this.userRepository = userRepository;
    }

    public UserProfile createUserProfile(UserProfileRequestDTO userProfileRequestDTO) {
        User user = userRepository.findById(userProfileRequestDTO.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Mapear los links del JSON usando Record
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
}