package com.company.projects.course.coursemanagementsystem.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService {

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public UserDetails getCurrentUserDetails() {
        Authentication authentication = getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }

    public String getCurrentUserRole() {
        UserDetails userDetails = getCurrentUserDetails();
        if (userDetails != null) {
            return userDetails.getAuthorities().stream()
                    .map(authority -> authority.getAuthority())
                    .filter(authority -> authority.startsWith("ROLE_"))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }
}
