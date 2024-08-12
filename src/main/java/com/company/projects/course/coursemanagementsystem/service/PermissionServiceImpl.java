package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.PermissionDto;
import com.company.projects.course.coursemanagementsystem.mapper.PermissionMapper;
import com.company.projects.course.coursemanagementsystem.model.PermissionEntity;
import com.company.projects.course.coursemanagementsystem.repository.PermissionRepository;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<String, PermissionDto, PermissionEntity> implements PermissionService{
    public PermissionServiceImpl(PermissionRepository repository, PermissionMapper mapper) {
        super(repository, mapper, "Permission");
    }
}
