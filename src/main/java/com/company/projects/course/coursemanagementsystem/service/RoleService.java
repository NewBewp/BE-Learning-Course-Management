package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.PermissionDto;
import com.company.projects.course.coursemanagementsystem.dto.RoleDto;

public interface RoleService extends BaseService<String, RoleDto>{
    RoleDto addPermission(String id, PermissionDto permissionDto);
    RoleDto removePermission(String id, PermissionDto permissionDto);
}
