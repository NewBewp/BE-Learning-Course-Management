package com.company.projects.course.coursemanagementsystem.dto;

import com.company.projects.course.coursemanagementsystem.validation.group.ValidationGroups;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty(message = "User must not be empty", groups = {ValidationGroups.Common.class})
    @Valid
    UserDto user;

    @NotEmpty(message = "Role must not be empty", groups = {ValidationGroups.Common.class})
    @Valid
    RoleDto role;
}

