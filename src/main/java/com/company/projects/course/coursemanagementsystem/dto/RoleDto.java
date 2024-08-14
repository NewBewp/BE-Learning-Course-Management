package com.company.projects.course.coursemanagementsystem.dto;

import com.company.projects.course.coursemanagementsystem.repository.RoleRepository;
import com.company.projects.course.coursemanagementsystem.validation.annotation.Unique;
import com.company.projects.course.coursemanagementsystem.validation.group.ValidationGroups;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.util.Collection;

/**
 * DTO for {@link com.company.projects.course.coursemanagementsystem.model.RoleEntity}
 */
@Value
@Builder
public class RoleDto implements Serializable {
    String id;
    @NotBlank(message = ValidationMessage.NAME_NOT_BLACK_MESSAGE, groups = {ValidationGroups.Common.class})
    @Size(min = 2, message = ValidationMessage.SIZE_NAME_MESSAGE, groups = {ValidationGroups.Common.class})
    @Unique(fieldName = "role", caseSensitive = false, repository = RoleRepository.class, groups = {ValidationGroups.Common.class})
    String name;

    @NotEmpty(message = "Permissions must not be empty", groups = {ValidationGroups.Common.class})
    @Valid
    Collection<PermissionDto> permissions;

//    Collection<AccountDto> accounts;
}