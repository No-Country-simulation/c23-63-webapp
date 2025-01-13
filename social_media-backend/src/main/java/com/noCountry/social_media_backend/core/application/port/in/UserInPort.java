package com.noCountry.social_media_backend.core.application.port.in;

import com.noCountry.social_media_backend.core.application.dto.UserDto;

import java.util.List;

public interface UserInPort {
    UserDto registerUser(UserDto userDto);
    List<UserDto> userList();
    UserDto userById(String id);
}
