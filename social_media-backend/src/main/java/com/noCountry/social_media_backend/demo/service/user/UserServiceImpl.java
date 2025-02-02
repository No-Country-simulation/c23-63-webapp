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
import java.util.Optional;

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
    public User usuarioPorCorreo(String email) {
        return this.usuarioRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

    @Override
    public Optional<User> usuarioPorCorreoGoogle(String email) {
        return this.usuarioRepository.findByEmail(email);
    }

    @Override
    public Optional<User> crearUsuarioGoogle(String name, String email) {
        User nuevoUsuario = User.builder()
                .username(name)
                .email(email)
                .createdAt(LocalDateTime.now())
                .password("")
                .role(Role.USER).build();
        return Optional.of(this.usuarioRepository.save(nuevoUsuario));
    }
}
