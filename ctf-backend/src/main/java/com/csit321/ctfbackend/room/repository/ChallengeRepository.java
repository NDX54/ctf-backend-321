package com.csit321.ctfbackend.room.repository;

import com.csit321.ctfbackend.room.model.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

    @Query("select c from Challenge c where c.isChallengeOpen = true")
    List<Challenge> findAllOpenChallenges();

    @Query("select c from Challenge c where c.isChallengeOpen = false")
    List<Challenge> findAllClosedChallenges();

}
