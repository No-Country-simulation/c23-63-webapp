package com.noCountry.social_media_backend.demo.service.auth;

import com.noCountry.social_media_backend.demo.dto.*;
import com.noCountry.social_media_backend.demo.entity.user.User;
import com.noCountry.social_media_backend.demo.security.jwt.JwtService;
import com.noCountry.social_media_backend.demo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService usuarioService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponseDto login(LoginRequestDto loginRequestDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.email(), loginRequestDto.password()));
        } catch (AuthenticationException badCredentialsException) {

            if (badCredentialsException instanceof BadCredentialsException) {
                throw new BadCredentialsException("Usuario o contraseña ingresadas son inválidos.");
            }
        }

        User user = this.usuarioService.usuarioPorCorreo(loginRequestDto.email());
        String token = this.jwtService.getToken(user.getId(),user.getUsername(),user.getRole());

        String roleName = user.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("")
                .replace("ROLE_", "");

        UsuarioResponseDto usuarioAuth = new UsuarioResponseDto(user.getId(), user.getEmail(), roleName);
        return new AuthResponseDto(token, usuarioAuth);
    }

    @Override
    public ExitoResponseDto register(UsuarioCrearRequestDto usuarioCrearRequestDto) {
        return new ExitoResponseDto(this.usuarioService.crearUsuario(usuarioCrearRequestDto));
    }
}
