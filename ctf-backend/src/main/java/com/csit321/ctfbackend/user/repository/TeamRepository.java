package com.csit321.ctfbackend.user.repository;

import com.csit321.ctfbackend.user.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    Optional<Team> findByTeamPassword(String teamPassword);
    Optional<Team> findByTeamName(String teamName);

    @Query("SELECT t FROM Team t JOIN t.members u WHERE u.username = :username")
    Optional<Team> findTeamByUsers_Username(String username);
    List<Team> findByCompetition_CompetitionId(Long competitionId);

}
