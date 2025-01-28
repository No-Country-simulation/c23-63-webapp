package com.noCountry.social_media_backend.demo.repository;

import com.noCountry.social_media_backend.demo.entity.permission.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
}
