package com.noCountry.social_media_backend.demo.service.auth;

import com.noCountry.social_media_backend.demo.dto.AuthResponseDto;
import com.noCountry.social_media_backend.demo.dto.ExitoResponseDto;
import com.noCountry.social_media_backend.demo.dto.LoginRequestDto;
import com.noCountry.social_media_backend.demo.dto.UsuarioCrearRequestDto;

public interface AuthService {

    AuthResponseDto login(LoginRequestDto loginRequestDto);

    ExitoResponseDto register(UsuarioCrearRequestDto registerRequestDto);
}
