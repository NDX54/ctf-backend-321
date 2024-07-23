package com.csit321.ctfbackend.user.service;

import com.csit321.ctfbackend.user.model.BaseUser;
import com.csit321.ctfbackend.user.repository.BaseUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final BaseUserRepository baseUserRepository;

    @Autowired
    public CustomUserDetailsService(BaseUserRepository baseUserRepository) {
        this.baseUserRepository = baseUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return baseUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true, true, true, getAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(BaseUser baseUser) {
        return List.of(new SimpleGrantedAuthority(baseUser.getRole()));
    }
}
