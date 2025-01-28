package com.noCountry.social_media_backend.demo.entity.post.DTOs;

import com.noCountry.social_media_backend.demo.entity.post.Post;
import com.noCountry.social_media_backend.demo.entity.user.User;

import java.time.LocalDateTime;

public record DtoPost(
        Integer id,
        User user,
        String title,
        String content,
        String imageUrl,
        String category,
        LocalDateTime createdAt
) {

    //public DtoPost(Post post) {
   //     this(post.getId(),post.getUser(),post.getTitle(),post.getContent(),post.getImageUrl(),post.getCategory(),post.getCreatedAt());
    //}
}
