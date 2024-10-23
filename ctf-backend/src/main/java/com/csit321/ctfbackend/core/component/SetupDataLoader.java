package com.csit321.ctfbackend.core.component;

import com.csit321.ctfbackend.core.config.jwt.JwtService;
import com.csit321.ctfbackend.core.token.TokenRepository;
import com.csit321.ctfbackend.user.model.BaseUser;
import com.csit321.ctfbackend.user.model.enums.Role;
import com.csit321.ctfbackend.user.repository.BaseUserRepository;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final JwtService jwtService;
    private final BaseUserRepository baseUserRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;

    private static final Logger logger = Logger.getLogger(SetupDataLoader.class.getName());

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    boolean alreadySetup = false;


    @Override
    @Transactional
    public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {
        if (alreadySetup) {
            System.out.println("Already setup");
        }

        System.out.println("Not yet setup");

        createAdminUser();
        alreadySetup = true;
    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }

    private void createAdminUser() {

        var adminUser = BaseUser.baseUserBuilderEntity()
                .username(adminUsername)
                .email(adminEmail)
                .password(passwordEncoder.encode(adminPassword))
                .role(Role.ADMIN)
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();

        baseUserRepository.save(adminUser);
    }
}
