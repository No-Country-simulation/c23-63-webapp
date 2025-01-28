package com.noCountry.social_media_backend.demo.entity.user.DTOs;

import com.noCountry.social_media_backend.demo.entity.user.User;

import java.time.LocalDateTime;

public record DtoUser(
        Integer id,
        String username,
        String password,
        String email,
        LocalDateTime createdAt,
        String role

) {

    public DtoUser(User user) {
        this(user.getId(),user.getUsername(),user.getPassword(),user.getEmail(),user.getCreatedAt(),user.getRole());
    }
}
