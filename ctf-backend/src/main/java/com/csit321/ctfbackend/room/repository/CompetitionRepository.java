package com.csit321.ctfbackend.room.repository;

import com.csit321.ctfbackend.room.model.Competition;
import com.csit321.ctfbackend.user.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {

}
