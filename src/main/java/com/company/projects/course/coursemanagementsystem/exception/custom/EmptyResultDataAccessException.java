package com.company.projects.course.coursemanagementsystem.exception.custom;

public class EmptyResultDataAccessException extends CustomException{
    public EmptyResultDataAccessException(String message) {
        super(message);
    }

    public EmptyResultDataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
