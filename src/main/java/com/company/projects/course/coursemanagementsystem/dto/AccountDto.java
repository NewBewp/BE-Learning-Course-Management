package com.company.projects.course.coursemanagementsystem.dto;

import com.company.projects.course.coursemanagementsystem.repository.AccountRepository;
import com.company.projects.course.coursemanagementsystem.validation.annotation.Unique;
import com.company.projects.course.coursemanagementsystem.validation.group.ValidationGroups;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Username must not be blank")
    @Size(min = 10, message = "Username must have at least 10 characters")
    @Unique(fieldName = "username", caseSensitive = false, repository = AccountRepository.class,
            groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
    String username;

    @NotNull(message = "User must not be null", groups = {ValidationGroups.Create.class})
    @Valid
    UserDto user;

    @NotNull(message = "Role must not be null", groups = {ValidationGroups.Create.class})
    @Valid
    RoleDto role;
}

