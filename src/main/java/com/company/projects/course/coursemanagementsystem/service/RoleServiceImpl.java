package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.PermissionDto;
import com.company.projects.course.coursemanagementsystem.dto.RoleDto;
import com.company.projects.course.coursemanagementsystem.exception.custom.EmptyResultDataAccessException;
import com.company.projects.course.coursemanagementsystem.exception.custom.EntityNotFoundException;
import com.company.projects.course.coursemanagementsystem.mapper.PermissionMapper;
import com.company.projects.course.coursemanagementsystem.mapper.RoleMapper;
import com.company.projects.course.coursemanagementsystem.model.RoleEntity;
import com.company.projects.course.coursemanagementsystem.repository.RoleRepository;
import com.company.projects.course.coursemanagementsystem.service.custom.search.NameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RoleServiceImpl extends BaseServiceImpl<String, RoleDto, RoleEntity> implements RoleService, NameService<RoleDto> {
    private final RoleRepository roleRepository;
    private final PermissionMapper permissionMapper;
    private final RoleMapper roleMapper;
    @Autowired
    public RoleServiceImpl(RoleRepository repository, RoleMapper mapper, PermissionMapper permissionMapper, RoleMapper roleMapper) {
        super(repository, mapper, "Role");
        this.roleRepository = repository;
        this.permissionMapper = permissionMapper;
        this.roleMapper = roleMapper;
    }

    @Override
    public RoleDto addPermission(String id, PermissionDto permissionDto) {
        RoleEntity existingRole = roleRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with id " + id));
        existingRole.getPermissions().add(permissionMapper.toEntity(permissionDto));
        return roleMapper.toDto(roleRepository.save(existingRole));
    }

    @Override
    public RoleDto removePermission(String id, PermissionDto permissionDto) {
        RoleEntity existingRole = roleRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with id " + id));
        existingRole.getPermissions().removeIf(p -> p.getId().equals(permissionDto.getId()));
        return roleMapper.toDto(roleRepository.save(existingRole));
    }

    @Override
    public Collection<RoleDto> searchAllByName(String name) {
        Collection<RoleEntity> results = roleRepository.findAllByNameAndDeletedFalse(name);
        if (results.isEmpty()) throw new EmptyResultDataAccessException("Role" + " not found with name = " + name);
        return results.stream().map(roleMapper::toDto).toList();
    }
}

