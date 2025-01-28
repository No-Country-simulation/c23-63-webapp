package com.noCountry.social_media_backend.demo.repository;

import com.noCountry.social_media_backend.demo.entity.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
