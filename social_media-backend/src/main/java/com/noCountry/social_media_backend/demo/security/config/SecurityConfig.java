package com.noCountry.social_media_backend.demo.security.config;

import com.noCountry.social_media_backend.demo.constant.Role;
import com.noCountry.social_media_backend.demo.security.jwt.JwtAuthenticationFilter;
import com.noCountry.social_media_backend.demo.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;
    private final JwtService jwtService;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(
            HttpSecurity http,
            CorsConfigurationSource corsConfigurationSource,
            DefaultOAuth2UserService defaultOAuth2UserService
    ) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(corsCustomizer -> corsCustomizer.configurationSource(corsConfigurationSource))
                .authorizeHttpRequests(
                        authorizeRequest -> authorizeRequest
                                .requestMatchers("/auth/**").permitAll()
                                .requestMatchers("/posts/**").permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

//                .exceptionHandling(exception -> exception
//                        .authenticationEntryPoint((request, response, authException) -> {
////                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
//                            response.sendError(response.getStatus(), "Unauthorized");
//                        })
//                )
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(AbstractHttpConfigurer::disable)
                .oauth2Login(oauth -> oauth
                        .userInfoEndpoint(userInfo -> userInfo.userService(defaultOAuth2UserService))
                        .successHandler(authenticationSuccessHandler())
                );

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {

            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

            String name = (String) oAuth2User.getAttributes().get("name");
            Integer userId = (Integer) oAuth2User.getAttributes().get("userId");
            Role role = (Role) oAuth2User.getAttributes().get("role");

            String token = jwtService.getToken(userId, name, role);

            response.sendRedirect("http://localhost:5173/auth/verify-session?token=" + token);
        };
    }
}

