package com.csit321.ctfbackend.room.repository;

import com.csit321.ctfbackend.room.model.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {



}
