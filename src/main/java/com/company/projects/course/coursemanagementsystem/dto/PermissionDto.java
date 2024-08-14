package com.company.projects.course.coursemanagementsystem.dto;

import com.company.projects.course.coursemanagementsystem.repository.PermissionRepository;
import com.company.projects.course.coursemanagementsystem.validation.annotation.Unique;
import com.company.projects.course.coursemanagementsystem.validation.group.ValidationGroups;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.util.Collection;

/**
 * DTO for {@link com.company.projects.course.coursemanagementsystem.model.PermissionEntity}
 */
@Value
@Builder
public class PermissionDto implements Serializable {
    String id;
    @NotBlank(message = ValidationMessage.NAME_NOT_BLACK_MESSAGE, groups = {ValidationGroups.Common.class})
    @Size(min = 2, message = ValidationMessage.SIZE_NAME_MESSAGE, groups = {ValidationGroups.Common.class})
    @Unique(fieldName = "name", caseSensitive = false, repository = PermissionRepository.class, groups = {ValidationGroups.Common.class})
    String name;
//    Collection<RoleDto> roles;
}