package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.RoleDto;
import com.company.projects.course.coursemanagementsystem.mapper.RoleMapper;
import com.company.projects.course.coursemanagementsystem.model.RoleEntity;
import com.company.projects.course.coursemanagementsystem.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseServiceImpl<String, RoleDto, RoleEntity> implements RoleService {
    @Autowired
    public RoleServiceImpl(RoleRepository repository, RoleMapper mapper) {
        super(repository, mapper, "Role");
    }
}
