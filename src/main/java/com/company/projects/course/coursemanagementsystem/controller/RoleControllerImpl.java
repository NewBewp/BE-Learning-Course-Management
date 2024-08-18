package com.company.projects.course.coursemanagementsystem.controller;

import com.company.projects.course.coursemanagementsystem.dto.PermissionDto;
import com.company.projects.course.coursemanagementsystem.dto.RoleDto;
import com.company.projects.course.coursemanagementsystem.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleControllerImpl extends BaseControllerImpl<String, RoleDto, RoleService> implements RoleController {
    RoleService roleService;

    @Autowired
    public RoleControllerImpl(RoleService service, RoleService roleService) {
        super(service);
        this.roleService = roleService;
    }

    @Override
    @PutMapping("/{id}/add_permission")
    public ResponseEntity<RoleDto> addPermission(@PathVariable String id, PermissionDto permissionDto) {
        RoleDto roleDto = roleService.addPermission(id, permissionDto);
        return ResponseEntity.ok(roleDto);
    }

    @Override
    @PutMapping("/{id}/remove_permission")
    public ResponseEntity<RoleDto> removePermission(@PathVariable String id, PermissionDto permissionDto) {
        RoleDto roleDto = roleService.removePermission(id, permissionDto);
        return ResponseEntity.ok(roleDto);
    }

    @Override
    @GetMapping("/search")
    public ResponseEntity<Page<RoleDto>> search(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "updatedAt:desc") String sort) {
        Page<RoleDto> results = roleService.search(name, page, size, sort);
        return ResponseEntity.ok(results);
    }

}
