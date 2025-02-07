package com.noCountry.social_media_backend.demo.dto.infoUser;

import java.util.List;

public record UserProfileResponseDTO(
        String name,
        String jobTitle,
        Integer friends,
        boolean isFriend,
        List<LinkDTO> links
) {

}
