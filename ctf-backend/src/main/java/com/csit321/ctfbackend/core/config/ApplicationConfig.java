package com.csit321.ctfbackend.core.config;

import com.csit321.ctfbackend.user.repository.BaseUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final BaseUserRepository baseUserRepository;

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
    public UserDetailsService userDetailsService() {
        return username -> baseUserRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
