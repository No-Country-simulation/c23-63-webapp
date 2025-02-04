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
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        AuthResponseDto authResponseDto = authService.login(loginRequestDto);
        Cookie jwtCookie = new Cookie("SESSION_TOKEN", authResponseDto.token());
        jwtCookie.setHttpOnly(true);
        jwtCookie.setSecure(false); // info -> Cambiar a true en producción (HTTPS)
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(60 * 60 * 24); // 24 horas
        response.addCookie(jwtCookie);
        return ResponseEntity.ok(authResponseDto);
    }

    @PostMapping("register")
    public ResponseEntity<ExitoResponseDto> register(@RequestBody UsuarioCrearRequestDto usuarioCrearRequestDto) {
        return ResponseEntity.ok(this.authService.register(usuarioCrearRequestDto));
    }
}
