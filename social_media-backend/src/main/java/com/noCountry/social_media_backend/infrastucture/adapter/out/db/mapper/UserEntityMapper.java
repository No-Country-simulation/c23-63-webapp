package com.noCountry.social_media_backend.infrastucture.adapter.out.db.mapper;

import com.noCountry.social_media_backend.core.domain.model.User;
import com.noCountry.social_media_backend.infrastucture.adapter.out.db.entity.UserEntity;

public class UserEntityMapper {

    public static User toDomain(UserEntity userEntity) {
        if (userEntity == null) return null;
        return new User(userEntity.getId(), userEntity.getNames(), userEntity.getSurnames());
    }

    public static UserEntity toEntity(User user) {
        if (user == null) return null;
        return new UserEntity(user.getId(), user.getNames(), user.getSurnames());
    }
}