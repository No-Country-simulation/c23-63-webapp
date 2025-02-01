package com.noCountry.social_media_backend.demo.service.user;

import com.noCountry.social_media_backend.demo.dto.UsuarioCrearRequestDto;
import com.noCountry.social_media_backend.demo.entity.user.User;

public interface UserService {


    String crearUsuario(UsuarioCrearRequestDto usuarioCrearRequestDto);

    User usuarioPorCorreo(String username);
}
