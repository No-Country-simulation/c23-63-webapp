package com.noCountry.social_media_backend.demo.dto;

public record UsuarioCrearRequestDto(
        String email,
        String password,
        String username
){
}