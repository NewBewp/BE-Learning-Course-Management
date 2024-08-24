package com.company.projects.course.coursemanagementsystem.configuration.security;

import com.company.projects.course.coursemanagementsystem.security.CustomAuthenticationProvider;
import com.company.projects.course.coursemanagementsystem.security.JwtAuthenticationFilter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    JwtAuthenticationFilter jwtAuthenticationFilter;
    CustomAuthenticationProvider customAuthenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Tắt CSRF
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/auth/login/**").permitAll() // Cho phép truy cập không cần xác thực vào /auth/**
                        .anyRequest().authenticated() // Tất cả các yêu cầu khác cần xác thực
                ).sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Sử dụng JWT, không cần session
                );

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    /*
     * AuthenticationManager is a interface in Spring Security
     * used to authenticate login information and return Authentication if
     * successful else throw an exception
     */
    /*
     * AuthenticationManagerBuilder is a interface in Spring Security
     * provide methods to config AuthenticationManager. It is used to build
     * UserDetailsService, PasswordEncoder, AuthenticationProvider.
     */
    /*
     * UserDetailsService is a interface in Spring Security
     * used to load user details from database or other sources.
     * It provide loadUserByUsername to load user details and return UserDetails
     */
    /*
     * PasswordEncoder is a interface in Spring Security
     * used to encode password. It provide encode and matches methods
     */
    /*
     * HttpSecurity is a interface in Spring Security
     * used to configure HTTP request authorization. It provide methods to
     * authorizeHttpRequests, authorizeRequests, and configure other security settings.
     */
    /*
     * AuthenticationProvider is an interface in Spring Security
     * used to authenticate and return Authentication
     */

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider);
        return authenticationManagerBuilder.build();
    }
}
