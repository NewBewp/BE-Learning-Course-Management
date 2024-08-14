package com.company.projects.course.coursemanagementsystem.util;


import java.util.UUID;

public class PasswordUtil {
    public static String generatePassword() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 12);
    }
}
