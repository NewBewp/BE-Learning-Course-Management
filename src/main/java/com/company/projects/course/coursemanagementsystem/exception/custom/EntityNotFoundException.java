package com.company.projects.course.coursemanagementsystem.exception.custom;

public class EntityNotFoundException extends CustomException{
    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
