package com.noCountry.social_media_backend.demo.dto.posts;

import java.util.List;

public record PostResponseDTO(Integer userId, Integer countPosts, List<PostDTO> posts) {}