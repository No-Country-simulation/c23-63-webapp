package com.noCountry.social_media_backend.demo.entity.permission.DTOs;

import com.noCountry.social_media_backend.demo.entity.permission.Permission;
import com.noCountry.social_media_backend.demo.entity.user.User;

import java.time.LocalDateTime;

public record DtoPermission(
        Integer id,
        User user,
        String permission,
        LocalDateTime grantedAt
) {
    public DtoPermission(Permission permission) {
        this(permission.getId(),permission.getUser(),permission.getPermission(),permission.getGrantedAt());
    }
}
