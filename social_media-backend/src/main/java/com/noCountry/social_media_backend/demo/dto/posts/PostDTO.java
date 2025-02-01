package com.noCountry.social_media_backend.demo.dto.posts;

import java.time.LocalDateTime;
import java.util.List;

public record PostDTO(Integer postId, LocalDateTime createdAt, DescriptionDTO description, List<String> category) {}
