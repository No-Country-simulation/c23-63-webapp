package com.noCountry.social_media_backend.demo.entity.comment;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Integer postId;
    private Integer userId;
    private String comment_text;
    private Date created_at;
}
