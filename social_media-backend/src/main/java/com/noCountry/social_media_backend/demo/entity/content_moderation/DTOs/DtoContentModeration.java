package com.noCountry.social_media_backend.demo.entity.content_moderation.DTOs;

import com.noCountry.social_media_backend.demo.entity.comment.Comment;
import com.noCountry.social_media_backend.demo.entity.content_moderation.ContentModeration;
import com.noCountry.social_media_backend.demo.entity.post.Post;
import com.noCountry.social_media_backend.demo.entity.user.User;

import java.time.LocalDateTime;

public record DtoContentModeration(
        Integer id,
        Post post,
        User moderator,
        String action,
        LocalDateTime createdAt
) {

    public DtoContentModeration(ContentModeration contentModeration) {
        this(contentModeration.getId(),contentModeration.getPost(),contentModeration.getModerator(),contentModeration.getAction(),contentModeration.getCreatedAt());
    }
}
