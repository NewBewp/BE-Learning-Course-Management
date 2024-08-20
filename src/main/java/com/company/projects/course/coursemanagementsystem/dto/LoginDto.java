package com.company.projects.course.coursemanagementsystem.dto;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Value
@Builder
public class LoginDto implements Serializable {
    String username;
    String password;
}