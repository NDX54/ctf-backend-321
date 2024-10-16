package com.csit321.ctfbackend.core.config.security;

import com.csit321.ctfbackend.core.config.jwt.JwtAuthenticationFilter;
import com.csit321.ctfbackend.user.model.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
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
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;
    private final LogoutService logoutService;

    // List of public endpoints accessible without authentication
    private static final String[] AUTH_PUBLIC_WHITELIST = {
            "/swagger-ui.html/**",
            "/v3/api-docs/**",
            "/v2/api-docs/**",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/h2-console/**",
            "/api/users",
            "/api/user/**",
            "/ws/**",
            "/topic/**",
            "/app/**",
            "/api/team/**",
            "/api/competition/**",
            "/api/challenge/**",
            "/api/room/**",
            "/api/question/**",
            "/api/student/**"
    };

    // List of endpoints accessible only by admin users
    private static final String[] ADMIN_ENDPOINTS = {
            "/api/user/delete"
    };

    // List of endpoints accessible by both students and teachers
    private static final String[] STUDENT_TEACHER_ENDPOINTS = {
            "/api/challenge/**",
            "/api/room/**",
            "/api/question/**",
            "/api/student/**"
    };

    // Bean definition for role hierarchy configuration.
    // With ADMIN being the highest role and STUDENT being the lowest.
    @Bean
    public RoleHierarchy roleHierarchy() {
        return RoleHierarchyImpl.fromHierarchy(
                "ROLE_ADMIN > ROLE_MODERATOR > ROLE_TEACHER > ROLE_STUDENT"
        );
    }

    // Bean definition for custom web security expression handler.
    @Bean
    public DefaultWebSecurityExpressionHandler customWebSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy());
        return expressionHandler;
    }

    // Bean definition for security filter chain configuration.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(AUTH_PUBLIC_WHITELIST)
                        .permitAll()
                        .requestMatchers(ADMIN_ENDPOINTS)
                        .hasRole(Role.ADMIN.getValue())
                        .requestMatchers(STUDENT_TEACHER_ENDPOINTS)
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
                .logout(logout -> logout
                        .logoutUrl("/api/user/logout")
                        .addLogoutHandler(logoutService)
                        .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
                        .permitAll()
                )
                .build();
    }

}
