package com.noCountry.social_media_backend.core.application.port.out;

import com.noCountry.social_media_backend.core.domain.model.User;

import java.util.List;

public interface UserOutPort {
    User registerUser(User user);
    List<User> userList();
    User userById(String id);
}
