package com.noCountry.social_media_backend.demo.domain.user;

import com.noCountry.social_media_backend.demo.domain.user.DTOs.DtoUser;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Table(name = "users")
@Entity(name = "User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String role;
    private String password;
    private Date created_at;
    private String first_name;
    private String second_name;
    private String last_name;
    private String profile_photo;
    private String country;

    public User(DtoUser dtoUser) {
        this.id = dtoUser.id();
        this.username = dtoUser.username();
        this.first_name = dtoUser.first_name();
        this.second_name = dtoUser.second_name();
        this.last_name = dtoUser.last_name();
        this.profile_photo = dtoUser.profile_photo();
        this.country = dtoUser.country();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
