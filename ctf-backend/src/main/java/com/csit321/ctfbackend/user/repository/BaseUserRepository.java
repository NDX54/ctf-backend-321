package com.csit321.ctfbackend.user.repository;

import com.csit321.ctfbackend.user.dto.internal.BaseUserDTO;
import com.csit321.ctfbackend.user.model.BaseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BaseUserRepository extends JpaRepository<BaseUser, Long> {

    Optional<BaseUserDTO> findByUserId(Long userId);
    Optional<BaseUser> findByEmailIgnoreCase(String email);
    Optional<BaseUser> findByEmailIgnoreCaseOrUsername(String email, String userName);
    Optional<BaseUserDTO> findByEmailIgnoreCaseAndPassword(String email, String password);
    Optional<BaseUser> findByUsername(String userName);
    void deleteByEmailIgnoreCase(String email);
    void deleteByUsername(String userName);

}
