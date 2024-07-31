package com.csit321.ctfbackend.user.service;

import com.csit321.ctfbackend.user.repository.BaseUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final BaseUserRepository baseUserRepository;



}
