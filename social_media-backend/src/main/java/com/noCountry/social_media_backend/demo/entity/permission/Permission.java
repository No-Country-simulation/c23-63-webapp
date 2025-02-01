package com.noCountry.social_media_backend.demo.entity.permission;

import com.noCountry.social_media_backend.demo.entity.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "permission", schema = "social_media")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permission_id_seq")
    @SequenceGenerator(name = "permission_id_seq", sequenceName = "social_media.permission_id_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 50)
    private String permission;

    @Builder.Default
    @Column(name = "granted_at", nullable = false, updatable = false)
    private LocalDateTime grantedAt = LocalDateTime.now();
}
