package com.csit321.ctfbackend.room.repository;

import com.csit321.ctfbackend.room.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByRoom_RoomId(Long roomId);
}
