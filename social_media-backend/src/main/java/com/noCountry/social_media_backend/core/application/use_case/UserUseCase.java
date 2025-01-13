package com.noCountry.social_media_backend.core.application.use_case;

import com.noCountry.social_media_backend.core.application.dto.UserDto;
import com.noCountry.social_media_backend.core.application.mapper.UserMapper;
import com.noCountry.social_media_backend.core.application.port.in.UserInPort;
import com.noCountry.social_media_backend.core.application.port.out.UserOutPort;
import com.noCountry.social_media_backend.core.domain.exception.UserNotFoundException;
import com.noCountry.social_media_backend.core.domain.model.User;

import java.util.List;

public class UserUseCase implements UserInPort {

    private final UserOutPort userOutPort;

    public UserUseCase(UserOutPort userOutPort) {
        this.userOutPort = userOutPort;
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user = this.userOutPort.registerUser(UserMapper.toDomain(userDto));
        System.out.println(user);
        return UserMapper.toDto(user);
    }

    @Override
    public List<UserDto> userList() {
        return this.userOutPort.userList().stream().map(UserMapper::toDto).toList();
    }

    @Override
    public UserDto userById(String id) {
        User user = this.userOutPort.userById(id);
        if (user == null) {
            throw new UserNotFoundException("El usuario con id " + id + " no existe.");
        }

        return UserMapper.toDto(user);
    }
}
