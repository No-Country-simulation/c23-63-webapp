package com.noCountry.social_media_backend.demo.entity.user_profile.DTOs;

import com.noCountry.social_media_backend.demo.entity.user.User;
import com.noCountry.social_media_backend.demo.entity.user_profile.UserProfile;

public record DtoUserProfile(
        Integer id,
        User user,
        String name,
        String profilePhoto,
        String country,
        String discordLink,
        String linkedinLink,
        String githubLink,
        String youtubeLink,
        String personalLink,
        String jobTitle
) {
   // public DtoUserProfile(UserProfile userProfile) {
   //     this(userProfile.getId(),userProfile.getUser(),userProfile.getFirstName(),userProfile.getSecondName(),userProfile.getLastName(),
   //             userProfile.getProfilePhoto(),userProfile.getCountry(),userProfile.getDiscordLink(),userProfile.getLinkedinLink(),userProfile.getGithubLink(),userProfile.getYoutubeLink(),userProfile.getPersonalLink(),userProfile.getJobTitle());
   // }
}
