package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.EnrollmentDto;
import com.company.projects.course.coursemanagementsystem.exception.custom.EmptyResultDataAccessException;
import com.company.projects.course.coursemanagementsystem.mapper.EnrollmentMapper;
import com.company.projects.course.coursemanagementsystem.model.EnrollmentEntity;
import com.company.projects.course.coursemanagementsystem.repository.EnrollmentRepository;
import com.company.projects.course.coursemanagementsystem.repository.specification.EnrollmentSpecification;
import com.company.projects.course.coursemanagementsystem.util.JPAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EnrollmentServiceImpl extends BaseServiceImpl<String, EnrollmentDto, EnrollmentEntity> implements EnrollmentService{
    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentMapper enrollmentMapper;
    @Autowired
    public EnrollmentServiceImpl(EnrollmentRepository repository, EnrollmentMapper mapper) {
        super(repository, mapper, "Enrollment");
        this.enrollmentRepository = repository;
        this.enrollmentMapper = mapper;
    }

    @Override
    public Page<EnrollmentDto> filter(String status, LocalDate date, String courseId, String studentId, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, JPAUtil.getSortRequestParam(sort));
        Specification<EnrollmentEntity> spec = EnrollmentSpecification.filter(status, date, courseId, studentId);
        Page<EnrollmentEntity> results = enrollmentRepository.findAll(spec, pageable);
        if (results.isEmpty()) throw new EmptyResultDataAccessException("No results found");
        return results.map(enrollmentMapper::toDto);
    }
}

