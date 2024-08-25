package com.company.projects.course.coursemanagementsystem.controller;

import com.company.projects.course.coursemanagementsystem.dto.PermissionDto;
import com.company.projects.course.coursemanagementsystem.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permissions")
@PreAuthorize("hasRole('admin')")
public class PermissionControllerImpl extends BaseControllerImpl<String, PermissionDto, PermissionService> implements PermissionController {
    private final PermissionService permissionService;
    @Autowired
    public PermissionControllerImpl(PermissionService service) {
        super(service);
        this.permissionService = service;
    }


    @Override
    @PutMapping("/search")
    public ResponseEntity<Page<PermissionDto>> search(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "updatedAt:desc") String sort) {
        Page<PermissionDto> results = permissionService.search(name, page, size, sort);
        return ResponseEntity.ok(results);
    }
}
