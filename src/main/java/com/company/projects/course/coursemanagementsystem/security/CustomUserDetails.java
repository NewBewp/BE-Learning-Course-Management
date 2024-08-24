package com.company.projects.course.coursemanagementsystem.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {
    private String username;
    private String password;
    private String salt;
    private String companyId;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(String username, String password, String salt, String companyId, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.salt = salt;
        this.companyId = companyId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getCompanyId() {
        return companyId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Có thể tùy chỉnh
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Có thể tùy chỉnh
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Có thể tùy chỉnh
    }

    @Override
    public boolean isEnabled() {
        return true; // Có thể tùy chỉnh
    }
}