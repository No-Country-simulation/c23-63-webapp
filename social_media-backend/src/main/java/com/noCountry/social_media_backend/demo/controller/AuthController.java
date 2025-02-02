package com.noCountry.social_media_backend.demo.controller;


import com.noCountry.social_media_backend.demo.dto.AuthResponseDto;
import com.noCountry.social_media_backend.demo.dto.ExitoResponseDto;
import com.noCountry.social_media_backend.demo.dto.LoginRequestDto;
import com.noCountry.social_media_backend.demo.dto.UsuarioCrearRequestDto;
import com.noCountry.social_media_backend.demo.service.auth.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
//    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        AuthResponseDto authResponseDto = authService.login(loginRequestDto);
//        Cookie jwtCookie = new Cookie("token", authResponseDto.token());
//
//        jwtCookie.setHttpOnly(true);
//        jwtCookie.setSecure(true);
//        jwtCookie.setPath("/");
//        jwtCookie.setMaxAge(86400);
//
//        response.addCookie(jwtCookie);
        return ResponseEntity.ok(authResponseDto);
    }



    @PostMapping("register")
    public ResponseEntity<ExitoResponseDto> register(@RequestBody UsuarioCrearRequestDto usuarioCrearRequestDto) {
        return ResponseEntity.ok(this.authService.register(usuarioCrearRequestDto));
    }






}
