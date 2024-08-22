package com.company.projects.course.coursemanagementsystem.security;

import com.company.projects.course.coursemanagementsystem.util.Base64Util;
import com.company.projects.course.coursemanagementsystem.util.SHAUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class NoOpPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString(); // Không mã hóa mật khẩu
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return false;
    }

    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return PasswordEncoder.super.upgradeEncoding(encodedPassword);
    }

    public boolean matches(String rawPassword, String encodedPassword, String salt) {
        byte[] saltDecoded = Base64Util.decode(salt);
        try {
            return SHAUtil.checkPassword(rawPassword, encodedPassword, saltDecoded);
        } catch (Exception e) {
            return false;
        }
    }
}
