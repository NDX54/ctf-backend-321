package com.csit321.ctfbackend.user.service;

import com.csit321.ctfbackend.user.dto.external.PublicBaseUserDTO;
import com.csit321.ctfbackend.user.dto.internal.StudentDTO;
import com.csit321.ctfbackend.user.dto.internal.TeacherDTO;
import com.csit321.ctfbackend.user.dto.internal.BaseUserDTO;
import com.csit321.ctfbackend.user.model.BaseUser;
import com.csit321.ctfbackend.user.model.Student;
import com.csit321.ctfbackend.user.model.Teacher;

import java.util.List;

public interface BaseUserService {

    Student saveStudent(StudentDTO studentDTO);
    Teacher saveTeacher(TeacherDTO teacherDTO);
    BaseUserDTO convertToSpecificDTO(BaseUser baseUser);
    PublicBaseUserDTO convertToPublicDTO(BaseUser baseUser);
    StudentDTO convertToStudentDTO(Student student);
    TeacherDTO convertToTeacherDTO(Teacher teacher);
    BaseUserDTO getUser(String email, String username);
    PublicBaseUserDTO getPublicUser(String email, String username);
    BaseUserDTO authenticateUser(String email, String username, String password);
    BaseUserDTO updateUser(String email, String username, BaseUserDTO userDTO);
    void deleteUser(String email, String username);
    List<BaseUserDTO> getAllUsers();
    List<PublicBaseUserDTO> getAllPublicUsers();

}
