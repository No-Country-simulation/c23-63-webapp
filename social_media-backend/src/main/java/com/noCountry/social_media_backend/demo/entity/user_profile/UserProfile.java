package com.noCountry.social_media_backend.demo.entity.user_profile;

import com.noCountry.social_media_backend.demo.entity.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_profile", schema = "social_media")
public class UserProfile {

    @Id
    @Column(name = "id")
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "profile_photo", length = 255)
    private String profilePhoto;

    @Column(name = "discord_link", length = 255)
    private String discordLink;

    @Column(name = "linkedin_link", length = 255)
    private String linkedinLink;

    @Column(name = "github_link", length = 255)
    private String githubLink;

    @Column(name = "instagram_link", length = 255)
    private String instagramLink;

    @Column(name = "youtube_link", length = 255)
    private String youtubeLink;

    @Column(name = "personal_link", length = 255)
    private String personalLink;

    @Column(name = "job_title", length = 100)
    private String jobTitle;
}