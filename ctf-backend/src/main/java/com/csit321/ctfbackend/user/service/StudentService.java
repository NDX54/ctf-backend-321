package com.csit321.ctfbackend.user.service;

import com.csit321.ctfbackend.user.dto.internal.StudentDTO;
import com.csit321.ctfbackend.user.model.Student;

public interface StudentService {

    void saveStudentScore(String username, double score);

}
