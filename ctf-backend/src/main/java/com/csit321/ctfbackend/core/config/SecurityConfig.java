package com.csit321.ctfbackend.core.config;

import com.csit321.ctfbackend.user.model.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    private static final String[] AUTH_PUBLIC_WHITELIST = {
            "/swagger-ui.html/**",
            "/v3/api-docs/**",
            "/v2/api-docs/**",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/h2-console/**",
//            "/api/**",
            "/api/users",
            "/api/user/email/**",
            "/api/user/username/**",
            "/api/user/student",
            "/api/user/teacher",
            "/api/user/login",
            "/api/user/staff-login"
    };

    private static final String[] ADMIN_ENDPOINTS = {
            "/api/user/delete"
    };

    private static final String[] TEACHER_ENDPOINTS = {
            "/api/challenge/**",
            "/api/room/**",
            "/api/question/**"
    };

    private static final String[] STUDENT_ENDPOINTS = {
            "/api/student/**"
    };

    @Bean
    public RoleHierarchy roleHierarchy() {
        return RoleHierarchyImpl.fromHierarchy(
                "ROLE_ADMIN > ROLE_MODERATOR > ROLE_TEACHER > ROLE_STUDENT"
        );
    }

    @Bean
    public DefaultWebSecurityExpressionHandler customWebSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy());
        return expressionHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(AUTH_PUBLIC_WHITELIST)
                        .permitAll()
                        .requestMatchers(ADMIN_ENDPOINTS)
                        .hasRole(Role.ADMIN.getValue())
                        .requestMatchers(TEACHER_ENDPOINTS)
                        .hasRole(Role.TEACHER.getValue())
                        .requestMatchers(STUDENT_ENDPOINTS)
                        .hasRole(Role.STUDENT.getValue())
                        .anyRequest()
                        .authenticated()
                )
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
