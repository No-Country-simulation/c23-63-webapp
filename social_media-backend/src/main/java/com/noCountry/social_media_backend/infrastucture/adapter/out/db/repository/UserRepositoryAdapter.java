package com.noCountry.social_media_backend.infrastucture.adapter.out.db.repository;

import com.noCountry.social_media_backend.core.application.port.out.UserOutPort;
import com.noCountry.social_media_backend.core.domain.model.User;
import com.noCountry.social_media_backend.infrastucture.adapter.out.db.entity.UserEntity;
import com.noCountry.social_media_backend.infrastucture.adapter.out.db.mapper.UserEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserOutPort {

    private final UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        UserEntity userEntity = this.userRepository.save(UserEntityMapper.toEntity(user));
        return UserEntityMapper.toDomain(userEntity);
    }

    @Override
    public List<User> userList() {
        List<UserEntity> userEntities = this.userRepository.findAll();
        return userEntities.stream().map(UserEntityMapper::toDomain).toList();
    }

    @Override
    public User userById(String id) {
        Optional<UserEntity> userEntity = this.userRepository.findById(id);
        return userEntity.map(UserEntityMapper::toDomain).orElse(null);
    }
}
