package com.noCountry.social_media_backend.demo.dto.infoUser;

import java.util.List;

public record UserProfileRequestDTO(
        Integer userId,
        String name,
        String jobTitle,
        List<LinkDTO> links
) {
}
