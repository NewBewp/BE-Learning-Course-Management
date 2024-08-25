package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.EnrollmentDto;
import com.company.projects.course.coursemanagementsystem.exception.custom.EmptyResultDataAccessException;
import com.company.projects.course.coursemanagementsystem.mapper.EnrollmentMapper;
import com.company.projects.course.coursemanagementsystem.model.CourseEntity;
import com.company.projects.course.coursemanagementsystem.model.EnrollmentEntity;
import com.company.projects.course.coursemanagementsystem.repository.EnrollmentRepository;
import com.company.projects.course.coursemanagementsystem.repository.specification.EnrollmentSpecification;
import com.company.projects.course.coursemanagementsystem.util.JPAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EnrollmentServiceImpl extends BaseServiceImpl<String, EnrollmentDto, EnrollmentEntity> implements EnrollmentService{
    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentMapper enrollmentMapper;
    private final CurrentUserService currentUserService;
    @Autowired
    public EnrollmentServiceImpl(EnrollmentRepository repository, EnrollmentMapper mapper, CurrentUserService currentUserService) {
        super(repository, mapper, "Enrollment");
        this.enrollmentRepository = repository;
        this.enrollmentMapper = mapper;
        this.currentUserService = currentUserService;
    }

    public Page<EnrollmentEntity> filterByCompany(Page<EnrollmentEntity> results, Pageable pageable) {
        if (currentUserService.getCurrentUserRole().equals("ROLE_admin")) return results;
        List<EnrollmentEntity> filteredResults = results.stream()
                .filter(e -> e.getCourse().getCompany().getId().equals(currentUserService.getCurrentUserDetails().getCompanyId())).toList();

        if (filteredResults.isEmpty()) {
            throw new EmptyResultDataAccessException("Course is empty");
        }

        return new PageImpl<>(filteredResults, pageable, results.getTotalElements());
    }

    @Override
    public Page<EnrollmentDto> findAll(int page, int size, String sort) {
        Sort sortBy = JPAUtil.getSortRequestParam(sort);
        Pageable pageable = PageRequest.of(page, size, sortBy);
        Page<EnrollmentEntity> results = enrollmentRepository.findAllByDeletedFalse(pageable);
        if (results.isEmpty()) throw new EmptyResultDataAccessException("Enrollment" + " is empty");
        return filterByCompany(results, pageable).map(enrollmentMapper::toDto);
    }

    @Override
    public Page<EnrollmentDto> filter(String status, LocalDate date, String courseId, String studentId, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, JPAUtil.getSortRequestParam(sort));
        Specification<EnrollmentEntity> spec = EnrollmentSpecification.filter(status, date, courseId, studentId);
        Page<EnrollmentEntity> results = enrollmentRepository.findAll(spec, pageable);
        if (results.isEmpty()) throw new EmptyResultDataAccessException("No results found");
        return filterByCompany(results, pageable).map(enrollmentMapper::toDto);
    }
}

