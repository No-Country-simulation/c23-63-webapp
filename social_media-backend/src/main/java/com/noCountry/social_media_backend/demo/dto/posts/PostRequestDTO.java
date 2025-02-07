package com.noCountry.social_media_backend.demo.dto.posts;

import java.util.List;

public record PostRequestDTO(
        Integer userId,
        String title,
        String content,
        String category
) {
}
