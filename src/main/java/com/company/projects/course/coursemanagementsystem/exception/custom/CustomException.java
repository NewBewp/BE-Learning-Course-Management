package com.company.projects.course.coursemanagementsystem.exception.custom;

import java.io.Serial;

public class CustomException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }
}
