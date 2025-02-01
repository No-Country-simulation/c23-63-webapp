package com.noCountry.social_media_backend.demo.controller;

import com.noCountry.social_media_backend.demo.dto.posts.PostDTO;
import com.noCountry.social_media_backend.demo.dto.posts.PostRequestDTO;
import com.noCountry.social_media_backend.demo.dto.posts.PostResponseDTO;
import com.noCountry.social_media_backend.demo.entity.post.Post;
import com.noCountry.social_media_backend.demo.service.post.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{userId}")
    public PostResponseDTO getPostsByUserId(@PathVariable Integer userId) {
        return postService.getPostsByUserId(userId);
    }

    @PostMapping("/crear")
    public ResponseEntity<Post> createPost(@RequestBody PostRequestDTO postRequestDTO) {
        Post newPost = postService.savePost(postRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPost);
    }

}