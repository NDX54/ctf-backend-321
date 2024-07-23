package com.csit321.ctfbackend.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] AUTH_WHITELIST = {
            "/swagger-ui.html/**",
            "/v3/api-docs/**",
            "/v2/api-docs/**",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/h2-console/**",
            "/api/**"
//            "/api/users",
//            "/api/user/email/**",
//            "/api/user/username/**",
//            "/api/user/student",
//            "/api/user/teacher",
//            "/api/user/login"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        int cpuCost = (int) Math.pow(2, 14);
        String encodingId = "argon2";
        Map<String, PasswordEncoder> encoders = new HashMap<>();

        encoders.put("bcrypt", new BCryptPasswordEncoder(11, new SecureRandom()));
        encoders.put("scrypt", new SCryptPasswordEncoder(cpuCost,8,1,32,64));
        encoders.put("argon2", new Argon2PasswordEncoder(16, 32, 1, 4096, 3));

        return new DelegatingPasswordEncoder(encodingId, encoders);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .disable()
                )
                .addFilterAfter(new CsrfHeaderFilter(CookieCsrfTokenRepository.withHttpOnlyFalse()), CsrfFilter.class)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(AUTH_WHITELIST).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(AbstractHttpConfigurer::disable)
                .headers((headers) -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                )
//                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
//                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .build();
    }

}
