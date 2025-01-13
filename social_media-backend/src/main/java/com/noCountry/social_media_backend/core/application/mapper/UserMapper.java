package com.noCountry.social_media_backend.core.application.mapper;

import com.noCountry.social_media_backend.core.application.dto.UserDto;
import com.noCountry.social_media_backend.core.domain.model.User;

public class UserMapper {

    public static UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getNames(), user.getSurnames());
    }

    public static User toDomain(UserDto dto) {
        return new User(dto.names(), dto.surnames());
    }

}
