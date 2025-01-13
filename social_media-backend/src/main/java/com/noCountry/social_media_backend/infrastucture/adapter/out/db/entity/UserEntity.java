package com.noCountry.social_media_backend.infrastucture.adapter.out.db.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "\"user\"")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String names;
    private String surnames;

    public UserEntity(String names, String surnames) {
        this.names = names;
        this.surnames = surnames;
    }
}
