package com.company.projects.course.coursemanagementsystem.exception.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {
    LocalDateTime timestamp;
    Integer status;
    String error;
    String message;
    String path;
    String detail;
}
