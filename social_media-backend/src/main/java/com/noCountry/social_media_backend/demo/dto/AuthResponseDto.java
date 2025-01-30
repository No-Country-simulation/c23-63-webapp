package com.noCountry.social_media_backend.demo.dto;

public record AuthResponseDto(
        String token,
        UsuarioResponseDto usuarioAuth
) {

}
