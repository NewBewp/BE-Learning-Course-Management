package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.PermissionDto;
import com.company.projects.course.coursemanagementsystem.dto.RoleDto;
import org.springframework.data.domain.Page;

public interface RoleService extends BaseService<String, RoleDto>{
    RoleDto addPermission(String id, PermissionDto permissionDto);
    RoleDto removePermission(String id, PermissionDto permissionDto);
    Page<RoleDto> search(String name, int page, int size, String sort);
}
