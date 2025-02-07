package com.noCountry.social_media_backend.demo.repository;

import com.noCountry.social_media_backend.demo.entity.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Integer> {

    List<Post> findByUserId(Integer userId);

    Optional<Post> findById(Integer postId);


}
