package com.company.projects.course.coursemanagementsystem.controller;

import com.company.projects.course.coursemanagementsystem.exception.custom.EntityNotFoundException;
import com.company.projects.course.coursemanagementsystem.model.AccountEntity;
import com.company.projects.course.coursemanagementsystem.repository.AccountRepository;
import com.company.projects.course.coursemanagementsystem.security.JwtAuthenticationFilter;
import com.company.projects.course.coursemanagementsystem.security.JwtTokenProvider;
import com.company.projects.course.coursemanagementsystem.service.CurrentUserService;
import com.company.projects.course.coursemanagementsystem.service.EmailService;
import com.company.projects.course.coursemanagementsystem.util.Base64Util;
import com.company.projects.course.coursemanagementsystem.util.PasswordUtil;
import com.company.projects.course.coursemanagementsystem.util.SHAUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/password-reset")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PasswordResetController {

    EmailService emailService;
    CurrentUserService currentUserService;
    AuthenticationManager authenticationManager;
    JwtTokenProvider jwtTokenProvider;
    AccountRepository accountRepository;
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @PreAuthorize("hasAnyRole('admin', 'admin_company')")
    @PostMapping("/request")
    public ResponseEntity<String> requestPasswordReset(HttpServletRequest request) {
//        String token= jwtAuthenticationFilter.getJwtFromRequest(request);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int expirationInMinutes = 1;
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateTokenCustom(authentication, expirationInMinutes);
        String resetLink = "http://localhost:8080/api/password-reset/reset-password?token=" + token;
        emailService.sendPasswordResetEmail(currentUserService.getCurrentUserDetails().getUsername(), resetLink);
        return ResponseEntity.ok("Password reset link sent to your email.");
    }

    @GetMapping("/reset-password")
    public String showResetPasswordPage(@RequestParam String token, Model model) {
        model.addAttribute("token", token);
        return "reset-password";
    }

    @PostMapping("/reset-password")
    public String resetPassword(HttpServletRequest request, @RequestBody Map<String, String> payload, Model model) {
        String token= jwtAuthenticationFilter.getJwtFromRequest(request);
        Claims claims = jwtTokenProvider.getClaimsFromToken(token);

        // Kiểm tra thời gian hết hạn của token
        if (claims.getExpiration().before(new Date())) {
            throw new RuntimeException("Token has expired");
        }

        String username = currentUserService.getCurrentUserDetails().getUsername();
        String newPassword = payload.get("newPassword");
        AccountEntity account = accountRepository.findByUsernameAndDeletedFalse(username)
                .orElseThrow(() -> new EntityNotFoundException("Not found"));
        byte[] salt = SHAUtil.generateSalt();
        try {
            String password = SHAUtil.hashPassword(newPassword, salt);
            account.setPassword(password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        account.setSalt(Base64Util.encode(salt));
        accountRepository.save(account);
        return "password-reset-success";
    }

    @GetMapping("/password-reset-success")
    public String showPasswordResetSuccessPage() {
        return "password-reset-success";
    }
}

