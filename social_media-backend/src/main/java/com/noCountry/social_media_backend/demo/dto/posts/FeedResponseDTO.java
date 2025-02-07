package com.noCountry.social_media_backend.demo.dto.posts;

import java.util.List;

public record FeedResponseDTO(
        Integer postId,
        UserDataDTO userData,
        ContentDTO content,
        List<String> category
) {
}
