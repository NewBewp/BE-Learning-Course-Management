package com.company.projects.course.coursemanagementsystem.controller;

import com.company.projects.course.coursemanagementsystem.dto.LoginDto;
import com.company.projects.course.coursemanagementsystem.security.JwtTokenProvider;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AuthController {
    AuthenticationManager authenticationManager;
    JwtTokenProvider jwtTokenProvider;
    /*
     * UsernamePasswordAuthenticationToken class used to create object containing username and password for client and return AuthenticationToken.
     * Authentication is a interface used to presentation for authentication information for client.
     * authenticationManager will convert authenticationToken to one or many AuthenticationProvider
     */
    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
        );

        /*
            * SecurityContextHolder is a util class in Spring Security, provide permission to access SecurityContext
            *
         */
        /*
            * SecurityContext is place to store credentials. It contain Authentication to presentation for authenticated client
         */
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);
    }
}

