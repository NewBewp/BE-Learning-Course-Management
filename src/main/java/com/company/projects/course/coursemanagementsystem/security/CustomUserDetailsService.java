package com.company.projects.course.coursemanagementsystem.security;

import com.company.projects.course.coursemanagementsystem.exception.custom.EntityNotFoundException;
import com.company.projects.course.coursemanagementsystem.model.AccountEntity;
import com.company.projects.course.coursemanagementsystem.repository.AccountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    AccountRepository accountRepository;

    @Override
    @Transactional
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountEntity account = accountRepository.findByUsernameAndDeletedFalse(username)
                .orElseThrow(() -> new EntityNotFoundException("Could not find account"));
//        Collection<GrantedAuthority> authorities = account.getRole().getPermissions().stream()
//                .map(permission -> new SimpleGrantedAuthority(permission.getName())).collect(Collectors.toList());
        // Thêm vai trò vào danh sách authorities
        // Lấy vai trò của tài khoản
        GrantedAuthority roleAuthority = new SimpleGrantedAuthority("ROLE_" + account.getRole().getName());

        // Lấy các quyền của vai trò
        Collection<GrantedAuthority> authorities = account.getRole().getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toList());

        // Thêm vai trò vào danh sách authorities
        authorities.add(roleAuthority);
        return new CustomUserDetails(account.getUsername(), account.getPassword(), account.getSalt(), authorities);
    }
}
