package com.noCountry.social_media_backend.demo.entity.like.DTOs;

import com.noCountry.social_media_backend.demo.entity.like.Like;
import com.noCountry.social_media_backend.demo.entity.post.Post;
import com.noCountry.social_media_backend.demo.entity.user.User;

import java.time.LocalDateTime;

public record DtoLike(
        Integer id,
        Post post,
        User user,
        LocalDateTime createdAt
) {
    public DtoLike(Like like) {
        this(like.getId(),like.getPost(),like.getUser(),like.getCreatedAt());
    }

}
