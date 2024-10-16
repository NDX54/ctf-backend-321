package com.csit321.ctfbackend.user.repository;

import com.csit321.ctfbackend.user.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select s from Student s where s not in (select t.members from Team t)")
    List<Student> getAllStudentsNotInTeam();
}
