package com.company.projects.course.coursemanagementsystem.exception.custom;

public class ValidationException extends CustomException{
    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
