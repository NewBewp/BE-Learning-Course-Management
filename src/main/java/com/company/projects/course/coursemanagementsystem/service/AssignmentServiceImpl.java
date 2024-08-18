package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.AssignmentDto;
import com.company.projects.course.coursemanagementsystem.exception.custom.EmptyResultDataAccessException;
import com.company.projects.course.coursemanagementsystem.mapper.AssignmentMapper;
import com.company.projects.course.coursemanagementsystem.model.AssignmentEntity;
import com.company.projects.course.coursemanagementsystem.repository.AssignmentRepository;
import com.company.projects.course.coursemanagementsystem.repository.specification.AssignmentSpecification;
import com.company.projects.course.coursemanagementsystem.util.CheckFieldUtil;
import com.company.projects.course.coursemanagementsystem.util.JPAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class AssignmentServiceImpl extends BaseServiceImpl<String, AssignmentDto, AssignmentEntity> implements AssignmentService{
    private final AssignmentRepository repository;
    private final AssignmentMapper mapper;

    @Autowired
    public AssignmentServiceImpl(AssignmentRepository repository, AssignmentMapper mapper, AssignmentMapper assignmentMapper) {
        super(repository, mapper, "Assignment");
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Page<AssignmentDto> filter(String userId, String classroomId, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, JPAUtil.getSortRequestParam(sort));
        Page<AssignmentEntity> results;
        Specification<AssignmentEntity> spec = AssignmentSpecification.filterByCriteria(userId, classroomId);
        results = repository.findAll(spec, pageable);
        if (results.isEmpty()) throw new EmptyResultDataAccessException("No results found");
        return results.map(mapper::toDto);
    }
}

