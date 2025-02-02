package com.noCountry.social_media_backend.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

//    @GetMapping("/info")
////    public Map<String,Object> userInfo(@AuthenticationPrincipal OAuth2User user) {
//    public Map<String,Object> userInfo(@AuthenticationPrincipal OidcUser user) {
//        user.getFullName();
//        user.getEmail();
//        System.out.println(user);
//        return user.getAttributes();
//    }
}
