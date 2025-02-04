package com.noCountry.social_media_backend.demo.security.oauth2;

import com.noCountry.social_media_backend.demo.entity.user.User;
import com.noCountry.social_media_backend.demo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserService userService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("OAuth2User loadUser");
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> customAttributes = new HashMap<>(oAuth2User.getAttributes());

        String email = (String) oAuth2User.getAttributes().get("email");
        String name = (String) oAuth2User.getAttributes().get("name");

        Optional<User> existingUser = this.userService.usuarioPorCorreoGoogle(email);

        if (existingUser.isEmpty()) {
            existingUser = this.userService.crearUsuarioGoogle(name, email);
        }

        existingUser.ifPresent(user -> {
            customAttributes.put("userId", user.getId());
            customAttributes.put("role", user.getRole());
        });

        // info -> Si pasas "email", significa que cuando accedas a oAuth2User.getName(), obtendrás el valor correspondiente al atributo "email".
        return new DefaultOAuth2User(oAuth2User.getAuthorities(), customAttributes, "email");
    }
}