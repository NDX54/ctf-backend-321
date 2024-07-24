package com.csit321.ctfbackend.room.repository;

import com.csit321.ctfbackend.room.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
