package com.csit321.ctfbackend.user.controller;

import com.csit321.ctfbackend.user.dto.internal.TeacherUpdateDTO;
import com.csit321.ctfbackend.user.service.BaseUserService;
import com.csit321.ctfbackend.user.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    private final TeacherService teacherService;
    private final BaseUserService baseUserService;

    @PutMapping("/edit")
    public String updateTeacher(@RequestParam String username, @RequestBody TeacherUpdateDTO teacherUpdateDTO) {
        TeacherUpdateDTO updatedTeacher = teacherService.updateTeacherInfo(username, teacherUpdateDTO);

        return baseUserService.generateNewToken(updatedTeacher.getUsername());
    }
}
