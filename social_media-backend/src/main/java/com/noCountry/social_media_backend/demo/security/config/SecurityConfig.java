package com.noCountry.social_media_backend.demo.security.config;

import com.noCountry.social_media_backend.demo.constant.Role;
import com.noCountry.social_media_backend.demo.security.jwt.JwtCookieFilter;
import com.noCountry.social_media_backend.demo.security.jwt.JwtService;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtCookieFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;
    private final JwtService jwtService;

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain oauth2SecurityFilterChain(HttpSecurity http, DefaultOAuth2UserService defaultOAuth2UserService) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequest ->
                        authorizeRequest
                                .anyRequest().permitAll()
                )
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2Login(oauth -> oauth
                        .userInfoEndpoint(userInfo -> userInfo.userService(defaultOAuth2UserService))
                        .successHandler(authenticationSuccessHandler())
                )
                .securityMatcher("/oauth2/authorization/google", "/login/oauth2/code/google");
        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain customSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequest ->
                        authorizeRequest
                                .requestMatchers("/auth/**").permitAll()
                                .requestMatchers(
                                        "/user/**",
                                        "/post/**"
                                ).authenticated()
                                .anyRequest().authenticated()
                )
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(AbstractHttpConfigurer::disable)
                .securityMatcher("/**");
        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

            String email = (String) oAuth2User.getAttributes().get("email");
            Integer userId = (Integer) oAuth2User.getAttributes().get("userId");
            Role role = (Role) oAuth2User.getAttributes().get("role");

            String token = jwtService.getToken(userId, email, role);

            Cookie jwtCookie = new Cookie("SESSION_TOKEN", token);
            jwtCookie.setHttpOnly(true);
            jwtCookie.setSecure(false); // info -> Cambiar a true en producción (HTTPS)
            jwtCookie.setPath("/");
            jwtCookie.setMaxAge(60 * 60 * 24); // 24 horas

            response.addCookie(jwtCookie);
            response.sendRedirect("http://localhost:5173/verify-session");
        };
    }
}

