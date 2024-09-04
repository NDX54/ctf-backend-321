package com.csit321.ctfbackend.user.repository;

import com.csit321.ctfbackend.user.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    Optional<Team> findByTeamPassword(String teamPassword);
    Optional<Team> findByTeamName(String teamName);

}
