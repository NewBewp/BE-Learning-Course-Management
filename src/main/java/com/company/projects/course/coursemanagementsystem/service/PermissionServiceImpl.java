package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.PermissionDto;
import com.company.projects.course.coursemanagementsystem.exception.custom.EmptyResultDataAccessException;
import com.company.projects.course.coursemanagementsystem.mapper.PermissionMapper;
import com.company.projects.course.coursemanagementsystem.model.PermissionEntity;
import com.company.projects.course.coursemanagementsystem.repository.PermissionRepository;
import com.company.projects.course.coursemanagementsystem.service.custom.search.NameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<String, PermissionDto, PermissionEntity> implements PermissionService, NameService<PermissionDto> {
    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;
    @Autowired
    public PermissionServiceImpl(PermissionRepository repository, PermissionMapper mapper) {
        super(repository, mapper, "Permission");
        this.permissionRepository = repository;
        this.permissionMapper = mapper;
    }

    @Override
    public Page<PermissionDto> searchAllByName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PermissionEntity> results = permissionRepository.findAllByNameAndDeletedFalse(name, pageable);
        if (results.isEmpty()) throw new EmptyResultDataAccessException("Permission" + " not found with name = " + name);
        return results.map(permissionMapper::toDto);
    }
}
