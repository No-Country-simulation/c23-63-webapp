package com.noCountry.social_media_backend.demo.security.jwt;

import com.noCountry.social_media_backend.demo.security.jwt.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

@Component
public class JwtCookieFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtCookieFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Obtener el usuario autenticado desde el contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            // Obtener el usuario (por ejemplo, el nombre de usuario o el objeto User)
            String username = authentication.getName();  // Asumiendo que el username está en el Authentication

            // Crear el token JWT utilizando JwtService
            //String jwtToken = jwtService.getToken(username);
            // Crear la cookie con el JWT
           // Cookie cookie = new Cookie("JWT_TOKEN", jwtToken);
            //cookie.setHttpOnly(true); // Evita acceso desde JavaScript
            //cookie.setSecure(true);   // Solo si estás utilizando HTTPS
            //cookie.setPath("/");      // Asegúrate de que sea accesible en toda la aplicación
            //cookie.setMaxAge(60 * 60 * 24); // 1 día

            // Añadir la cookie a la respuesta
           // response.addCookie(cookie);
        }

        // Continuar con el siguiente filtro
        filterChain.doFilter(request, response);
    }
}
