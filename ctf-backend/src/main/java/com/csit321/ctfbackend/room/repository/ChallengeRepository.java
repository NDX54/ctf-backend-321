package com.csit321.ctfbackend.room.repository;

import com.csit321.ctfbackend.room.model.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {



}
