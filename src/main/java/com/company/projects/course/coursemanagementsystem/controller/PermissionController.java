package com.company.projects.course.coursemanagementsystem.controller;

import com.company.projects.course.coursemanagementsystem.dto.PermissionDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface PermissionController extends BaseController<String, PermissionDto> {
    ResponseEntity<Page<PermissionDto>> search(String name, int page, int size, String sort);
}
