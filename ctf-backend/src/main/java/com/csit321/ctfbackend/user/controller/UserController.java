package com.csit321.ctfbackend.user.controller;

import com.csit321.ctfbackend.core.api.APIResponse;
import com.csit321.ctfbackend.core.auth.AuthenticationRequest;
import com.csit321.ctfbackend.user.dto.external.PublicBaseUserDTO;
import com.csit321.ctfbackend.user.dto.internal.BaseUserDTO;
import com.csit321.ctfbackend.user.dto.internal.StudentDTO;
import com.csit321.ctfbackend.user.dto.internal.TeacherDTO;
import com.csit321.ctfbackend.user.model.Student;
import com.csit321.ctfbackend.user.model.Teacher;
import com.csit321.ctfbackend.user.model.enums.Role;
import com.csit321.ctfbackend.user.service.BaseUserService;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final BaseUserService baseUserService;

    public UserController(BaseUserService baseUserService) {
        this.baseUserService = baseUserService;
    }

    @GetMapping("/users")
    public List<PublicBaseUserDTO> getAllUsers() {
        return baseUserService.getAllPublicUsers();
    }

    @GetMapping("/user/email/{email}")
    public PublicBaseUserDTO getUserByEmail(@PathVariable String email) {
        return baseUserService.getPublicUser(email, null);
    }

    @GetMapping("/user/username/{username}")
    public PublicBaseUserDTO getUserByUsername(@PathVariable String username) {
        return baseUserService.getPublicUser(null, username);
    }

    @PostMapping("/user/staff-login")
    public ResponseEntity<?> loginStaff(@RequestBody @Valid AuthenticationRequest authRequest, WebRequest request) {
        BaseUserDTO user = baseUserService.getUser(authRequest);

        if (user.getRole().equals(Role.STUDENT.getValue())) {
            return APIResponse.build("Not so fast!", "Students are not allowed to login here", HttpStatus.FORBIDDEN, request);
        } else {
            return APIResponse.build(baseUserService.authenticateUser(user), "Authenticated staff user", HttpStatus.OK, request);
        }
    }

    @PostMapping("/user/student")
    public ResponseEntity<?> createStudent(@RequestBody @Valid StudentDTO studentDTO, WebRequest request) {

        return APIResponse.build(baseUserService.saveStudent(studentDTO), "Successfully created student", HttpStatus.CREATED, request);
    }

    @PostMapping("/user/teacher")
    public ResponseEntity<?> createTeacher(@RequestBody @Valid TeacherDTO teacherDTO, WebRequest request) {

        return APIResponse.build(baseUserService.saveTeacher(teacherDTO), "Successfully created teacher", HttpStatus.CREATED, request);
    }

    @PermitAll
    @PostMapping("/user/login")
    public ResponseEntity<?> loginUser(@RequestBody BaseUserDTO userDTO, WebRequest request) {
//        BaseUserDTO authUser = baseUserService.authenticateUser(userDTO.getEmail(), userDTO.getUsername(), userDTO.getPassword());

        return APIResponse.build(baseUserService.authenticateUser(userDTO), "Authenticated user", HttpStatus.OK, request);
    }

    @PutMapping("/user")
    public ResponseEntity<?> updateUser(@RequestParam("email") String email, @RequestParam("username") String username, @RequestBody @Valid BaseUserDTO userDTO, WebRequest request) {
        BaseUserDTO updatedUser = baseUserService.updateUser(email, username, userDTO);

        return APIResponse.build(updatedUser, "User Updated successfully", HttpStatus.OK, request);
    }

    @DeleteMapping("/user/delete")
    public ResponseEntity<?> deleteUser(@RequestParam("email") String email, @RequestParam("username") String username, WebRequest request) {
        baseUserService.deleteUser(email, username);

        return APIResponse.build(email, "User deleted successfully", HttpStatus.OK, request);
    }

}


