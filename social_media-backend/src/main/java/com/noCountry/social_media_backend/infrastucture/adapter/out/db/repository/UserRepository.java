package com.noCountry.social_media_backend.infrastucture.adapter.out.db.repository;

import com.noCountry.social_media_backend.infrastucture.adapter.out.db.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
}
