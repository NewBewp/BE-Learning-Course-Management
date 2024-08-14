package com.company.projects.course.coursemanagementsystem.dto;

public class ValidationMessage {
    public static final String NAME_NOT_BLACK_MESSAGE = "Name must not be blank";
    public static final String PHONE_NOT_BLACK_MESSAGE = "Phone must not be blank";
    public static final String EMAIL_NOT_BLACK_MESSAGE = "Email must not be blank";

    public static final String SIZE_NAME_MESSAGE = "Name must have at least 2 characters";
    public static final String SIZE_PHONE_MESSAGE = "Phone must have at least 10 characters and at most 11 characters";

    public static final String SIZE_EMAIL_MESSAGE = "Email must be between 3 and 254 characters";
    public static final String EMAIL_VALID_MESSAGE = "Email should be valid";

    public static final String PATTERN_PHONE = "^(05|07|08|09)\\\\d{8}$";
    public static final String PATTERN_MESSAGE = "Phone number must be a valid Vietnamese mobile number";

    public static final String PATTERN_EMAIL = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
}
