package com.company.projects.course.coursemanagementsystem.dto;

import com.company.projects.course.coursemanagementsystem.validation.group.ValidationGroups;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "User must not be null", groups = {ValidationGroups.Common.class})
    @Valid
    UserDto user;

    @NotNull(message = "Role must not be null", groups = {ValidationGroups.Common.class})
    @Valid
    RoleDto role;
}

