package com.noCountry.social_media_backend.demo.repository;

import com.noCountry.social_media_backend.demo.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    UserDetails findByUsername(String username);
    Optional<User> findAccountByUsername(String username);
    Optional<User> findAccountByEmail(String email);

}
