package com.noCountry.social_media_backend.demo.entity.content_moderation;

import com.noCountry.social_media_backend.demo.entity.post.Post;
import com.noCountry.social_media_backend.demo.entity.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "content_moderation", schema = "social_media")
public class ContentModeration {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "content_moderation_id_seq")
    @SequenceGenerator(name = "content_moderation_id_seq", sequenceName = "social_media.content_moderation_id_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "moderator_id", nullable = false)
    private User moderator;

    @Column(nullable = false, length = 50)
    private String action;

    @Column
    private String reason;

    @Builder.Default
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}