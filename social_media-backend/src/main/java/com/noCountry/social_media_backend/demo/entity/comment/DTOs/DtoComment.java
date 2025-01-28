package com.noCountry.social_media_backend.demo.entity.comment.DTOs;

import com.noCountry.social_media_backend.demo.entity.comment.Comment;
import com.noCountry.social_media_backend.demo.entity.post.Post;
import com.noCountry.social_media_backend.demo.entity.user.User;

import java.time.LocalDateTime;

public record DtoComment(
        Integer id,
        Post post,
        User user,
        String content,
        LocalDateTime createdAt
) {
   // public DtoComment(Comment comment) {
   //     this(comment.getId(),comment.getPost(),comment.getUser(),comment.getContent(),comment.getCreatedAt());
   // }
}
