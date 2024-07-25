package com.csit321.ctfbackend.core.auth;

import com.csit321.ctfbackend.core.config.JwtService;
import com.csit321.ctfbackend.user.model.BaseUser;
import com.csit321.ctfbackend.user.repository.BaseUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final BaseUserRepository baseUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

//    public AuthenticationResponse register(RegisterRequest request) {
//        var user = BaseUser.builder()
//                .firstName(request.getFirstName())
//                .lastName(request.getLastName())
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .role(Role.STUDENT)
//                .build();
//
//        baseUserRepository.save(user);
//        var jwtToken = jwtService.generateToken(user);
//
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//    }
//
//    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()
//                )
//        );
//
//        var user = baseUserRepository.findByEmail(request.getEmail()).orElseThrow();
//        var jwtToken = jwtService.generateToken(user);
//
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//    }
}
