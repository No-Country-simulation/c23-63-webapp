package com.noCountry.social_media_backend.demo.controller;


import com.noCountry.social_media_backend.demo.dto.AuthResponseDto;
import com.noCountry.social_media_backend.demo.dto.ExitoResponseDto;
import com.noCountry.social_media_backend.demo.dto.LoginRequestDto;
import com.noCountry.social_media_backend.demo.dto.UsuarioCrearRequestDto;
import com.noCountry.social_media_backend.demo.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(this.authService.login(loginRequestDto));
    }

    @PostMapping("register")
    public ResponseEntity<ExitoResponseDto> register(@RequestBody UsuarioCrearRequestDto usuarioCrearRequestDto) {
        return ResponseEntity.ok(this.authService.register(usuarioCrearRequestDto));
    }
}
