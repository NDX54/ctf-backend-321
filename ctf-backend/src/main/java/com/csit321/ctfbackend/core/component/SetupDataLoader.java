package com.csit321.ctfbackend.core.component;

import com.csit321.ctfbackend.user.enums.UserType;
import com.csit321.ctfbackend.user.model.BaseUser;
import com.csit321.ctfbackend.user.repository.BaseUserRepository;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    boolean alreadySetup = false;

    private BaseUserRepository baseUserRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public SetupDataLoader(BaseUserRepository baseUserRepository, PasswordEncoder passwordEncoder) {
        this.baseUserRepository = baseUserRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
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

        BaseUser adminUser = new BaseUser();
        adminUser.setUsername(adminUsername);
        adminUser.setEmail(adminEmail);
        adminUser.setPassword(passwordEncoder.encode(adminPassword));
        adminUser.setUserType(UserType.BASE_USER);
        adminUser.setRole("ROLE_ADMIN");
        adminUser.setAccountNonExpired(true);
        adminUser.setAccountNonLocked(true);
        adminUser.setCredentialsNonExpired(true);
        adminUser.setEnabled(true);
        baseUserRepository.save(adminUser);

    }
}
