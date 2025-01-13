package com.noCountry.social_media_backend.core.application.dto;

public record ErrorResponseDto(
        String message,
        int status
) {
}

