package com.noCountry.social_media_backend.demo.domain.user.DTOs;

import com.noCountry.social_media_backend.demo.domain.user.User;

public record DtoUser(
        Integer id,
        String username,
        String first_name,
        String second_name,
        String last_name,
        String profile_photo,
        String country
) {

    public DtoUser(User user) {
        this(user.getId(),user.getUsername(),user.getFirst_name(),user.getSecond_name(),user.getLast_name(),user.getProfile_photo(),user.getCountry());
    }
}
