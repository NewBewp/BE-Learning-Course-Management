package com.company.projects.course.coursemanagementsystem.controller;

import com.company.projects.course.coursemanagementsystem.dto.PermissionDto;
import com.company.projects.course.coursemanagementsystem.dto.RoleDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface RoleController extends BaseController<String, RoleDto> {
    ResponseEntity<RoleDto> addPermission(String id, PermissionDto permissionDto);
    ResponseEntity<RoleDto> removePermission(String id, PermissionDto permissionDto);
    ResponseEntity<Page<RoleDto>> search(String name, int page, int size, String sort);
}
