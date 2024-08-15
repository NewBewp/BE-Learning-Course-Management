package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.PermissionDto;
import com.company.projects.course.coursemanagementsystem.mapper.PermissionMapper;
import com.company.projects.course.coursemanagementsystem.model.PermissionEntity;
import com.company.projects.course.coursemanagementsystem.repository.PermissionRepository;
import com.company.projects.course.coursemanagementsystem.repository.custom.search.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<String, PermissionDto, PermissionEntity> implements PermissionService{
    @Autowired
    public PermissionServiceImpl(PermissionRepository repository, PermissionMapper mapper, SearchRepository<PermissionEntity, String> searchRepository) {
        super(repository, mapper, "Permission", searchRepository);
    }
}
