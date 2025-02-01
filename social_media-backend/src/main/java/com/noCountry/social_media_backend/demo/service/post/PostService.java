package com.noCountry.social_media_backend.demo.service.post;

import com.noCountry.social_media_backend.demo.dto.posts.DescriptionDTO;
import com.noCountry.social_media_backend.demo.dto.posts.PostDTO;
import com.noCountry.social_media_backend.demo.dto.posts.PostRequestDTO;
import com.noCountry.social_media_backend.demo.dto.posts.PostResponseDTO;
import com.noCountry.social_media_backend.demo.entity.post.Post;
import com.noCountry.social_media_backend.demo.entity.user.User;
import com.noCountry.social_media_backend.demo.repository.PostRepository;
import com.noCountry.social_media_backend.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostService {

    @Autowired
    private final PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponseDTO getPostsByUserId(Integer userId) {
        List<Post> posts = postRepository.findByUserId(userId);

        List<PostDTO> postDTOs = posts.stream().map(post ->
                new PostDTO(
                        post.getId(),
                        post.getCreatedAt(),
                        new DescriptionDTO(post.getTitle(),post.getImageUrl()),
                        Arrays.stream(post.getCategory().split(","))
                                .map(String::trim)  // Eliminar posibles espacios extra
                                .collect(Collectors.toList())  // Convertirlo en una lista
                )
        ).collect(Collectors.toList());

        return new PostResponseDTO(userId, postDTOs.size(), postDTOs);
    }

    public Post savePost(PostRequestDTO postRequestDto) {
        User user = userRepository.findById(postRequestDto.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = Post.builder()
                .user(user)
                .title(postRequestDto.description().title())
                .imageUrl(postRequestDto.description().imageUrl())
                .category(String.join(",", postRequestDto.category())) // Concatenar las categorías
                .createdAt(LocalDateTime.parse(postRequestDto.createdAt()))
                .build();

        // Guardar el post
        return postRepository.save(post);
    }
}
