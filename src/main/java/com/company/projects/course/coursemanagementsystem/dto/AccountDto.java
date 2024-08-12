package com.company.projects.course.coursemanagementsystem.dto;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.company.projects.course.coursemanagementsystem.model.AccountEntity}
 */
@Value
@Builder
public class AccountDto implements Serializable {
    String id;
    String username;
    UserDto user;
    RoleDto role;
}

