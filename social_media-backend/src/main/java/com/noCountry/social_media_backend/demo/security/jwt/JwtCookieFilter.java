
package com.noCountry.social_media_backend.demo.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtCookieFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {

            final String username;

            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("SESSION_TOKEN".equals(cookie.getName())) {

                        String token = cookie.getValue();
                        username = jwtService.getUsernameFromToken(token);

                        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                            if (jwtService.isTokenValid(token, userDetails)) {
                                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                        userDetails,
                                        null,
                                        userDetails.getAuthorities());

                                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                                // info -> Si no se ejecuta retorna un error 403
                                SecurityContextHolder.getContext().setAuthentication(authToken);
                            }
                        }
                        break;
                    }
                }
            }
            filterChain.doFilter(request, response);

        } catch (ExpiredJwtException expiredJwtException) {
            handlerExceptionResolver.resolveException(request, response, null, new ExpiredJwtException(expiredJwtException.getHeader(), expiredJwtException.getClaims(), "Expiró la sesión."));
        }

    }
}
