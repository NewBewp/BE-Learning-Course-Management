package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.PermissionDto;
import org.springframework.data.domain.Page;

public interface PermissionService extends BaseService<String, PermissionDto> {
    Page<PermissionDto> search(String name, int page, int size, String sort);
}
