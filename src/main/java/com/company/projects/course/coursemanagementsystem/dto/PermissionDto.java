package com.company.projects.course.coursemanagementsystem.dto;

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
    String name;
    Collection<RoleDto> roles;
}