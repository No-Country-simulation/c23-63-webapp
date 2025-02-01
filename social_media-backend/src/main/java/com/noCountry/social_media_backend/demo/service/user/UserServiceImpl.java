package com.noCountry.social_media_backend.demo.service.user;

import com.noCountry.social_media_backend.demo.constant.Role;
import com.noCountry.social_media_backend.demo.dto.UsuarioCrearRequestDto;
import com.noCountry.social_media_backend.demo.entity.user.User;
import com.noCountry.social_media_backend.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public String crearUsuario(UsuarioCrearRequestDto usuarioCrearRequestDto) {
        User nuevoUsuario = User.builder()
                .email(usuarioCrearRequestDto.email())
                .password(this.passwordEncoder.encode(usuarioCrearRequestDto.password()))
                .username(usuarioCrearRequestDto.username())
                .createdAt(LocalDateTime.now())
                .role(Role.USER).build();

        this.usuarioRepository.save(nuevoUsuario);
        return "El usuario se creó con éxito";
    }

    @Override
    public User usuarioPorCorreo(String username) {
        return this.usuarioRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }
}
