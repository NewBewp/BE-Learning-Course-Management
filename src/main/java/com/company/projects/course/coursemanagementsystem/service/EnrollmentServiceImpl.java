package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.EnrollmentDto;
import com.company.projects.course.coursemanagementsystem.mapper.EnrollmentMapper;
import com.company.projects.course.coursemanagementsystem.model.EnrollmentEntity;
import com.company.projects.course.coursemanagementsystem.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentServiceImpl extends BaseServiceImpl<String, EnrollmentDto, EnrollmentEntity> implements EnrollmentService{
    @Autowired
    public EnrollmentServiceImpl(EnrollmentRepository repository, EnrollmentMapper mapper) {
        super(repository, mapper, "Enrollment");
    }
}
