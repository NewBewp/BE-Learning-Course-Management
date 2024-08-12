package com.company.projects.course.coursemanagementsystem.dto;

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
    String name;
    Collection<AccountDto> accounts;
    Collection<PermissionDto> permissions;
}