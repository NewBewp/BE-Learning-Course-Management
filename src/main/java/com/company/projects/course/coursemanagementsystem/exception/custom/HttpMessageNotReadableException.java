package com.company.projects.course.coursemanagementsystem.exception.custom;

public class HttpMessageNotReadableException extends RuntimeException{
    public HttpMessageNotReadableException(String message) {
        super(message);
    }

    public HttpMessageNotReadableException(String message, Throwable cause) {
        super(message, cause);
    }
}
