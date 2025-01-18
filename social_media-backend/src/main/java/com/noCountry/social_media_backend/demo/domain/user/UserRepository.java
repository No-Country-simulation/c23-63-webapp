package com.noCountry.social_media_backend.demo.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    UserDetails findByUsername(String username);
    Optional<User> findAccountByUsername(String username);
    Optional<User> findAccountByEmail(String email);

}
