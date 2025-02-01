package com.noCountry.social_media_backend.demo.dto.posts;

import java.util.List;

public record PostRequestDTO(
        Integer userId,
        String createdAt,
        DescriptionDTO description,
        List<String> category
) {
}
