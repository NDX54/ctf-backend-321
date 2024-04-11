package com.csit321.usermodule.repository;

import com.csit321.usermodule.controller.dto.UserDto;
import com.csit321.usermodule.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);

    User save(UserDto userDto);
}
