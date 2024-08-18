package com.company.projects.course.coursemanagementsystem.util;

import java.util.regex.Pattern;

public class CheckFieldUtil {
    public static boolean isValidUUID(String str) {
        String uuidRegex = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
        Pattern pattern = Pattern.compile(uuidRegex);
        return pattern.matcher(str).matches();
    }

    public static boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public static boolean isValidPhone(String phone) {
        return phone.matches("\\d{10,15}");
    }
}
