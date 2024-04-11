package com.csit321.usermodule.service;

import com.csit321.customerrorhandlers.CustomNotFoundException;
import com.csit321.usermodule.controller.dto.UserDto;
import com.csit321.usermodule.model.entities.User;
import com.csit321.usermodule.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new CustomNotFoundException("User not found."));
    }

    @Override
    public User save(UserDto userDto) {
        User user = new User(userDto.getUsername(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }

}
