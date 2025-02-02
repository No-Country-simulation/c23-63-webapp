package com.noCountry.social_media_backend.demo.service.user;

import com.noCountry.social_media_backend.demo.dto.UsuarioCrearRequestDto;
import com.noCountry.social_media_backend.demo.entity.user.User;

import java.util.Optional;

public interface UserService {

    String crearUsuario(UsuarioCrearRequestDto usuarioCrearRequestDto);

    User usuarioPorCorreo(String username);

    Optional<User> usuarioPorCorreoGoogle(String email);

    Optional<User> crearUsuarioGoogle(String name, String email);
}
