package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.AssignmentDto;
import com.company.projects.course.coursemanagementsystem.mapper.AssignmentMapper;
import com.company.projects.course.coursemanagementsystem.model.AssignmentEntity;
import com.company.projects.course.coursemanagementsystem.repository.AssignmentRepository;
import org.springframework.stereotype.Service;

@Service
public class AssignmentServiceImpl extends BaseServiceImpl<String, AssignmentDto, AssignmentEntity> implements AssignmentService{
    public AssignmentServiceImpl(AssignmentRepository repository, AssignmentMapper mapper) {
        super(repository, mapper, "Assignment");
    }
}
