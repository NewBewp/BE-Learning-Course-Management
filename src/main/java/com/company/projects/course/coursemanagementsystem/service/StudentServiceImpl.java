package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.StudentDto;
import com.company.projects.course.coursemanagementsystem.exception.custom.EmptyResultDataAccessException;
import com.company.projects.course.coursemanagementsystem.mapper.StudentMapper;
import com.company.projects.course.coursemanagementsystem.model.StudentEntity;
import com.company.projects.course.coursemanagementsystem.repository.StudentRepository;
import com.company.projects.course.coursemanagementsystem.repository.specification.StudentSpecification;
import com.company.projects.course.coursemanagementsystem.util.JPAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl extends BaseServiceImpl<String, StudentDto, StudentEntity> implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final StudentSpecification studentSpecification;
    private final CurrentUserService currentUserService;

    @Autowired
    public StudentServiceImpl(StudentRepository repository, StudentMapper mapper, StudentSpecification studentSpecification,
                              CurrentUserService currentUserService) {
        super(repository, mapper, "Student");
        this.studentRepository = repository;
        this.studentMapper = mapper;
        this.studentSpecification = studentSpecification;
        this.currentUserService = currentUserService;
    }

    public Page<StudentEntity> filterByCompany(Page<StudentEntity> results, Pageable pageable) {
        if (currentUserService.getCurrentUserRole().equals("ROLE_admin")) return results;
        List<StudentEntity> filteredResults = results.stream()
                .filter(e -> e.getEnrollments().stream()
                        .anyMatch(i -> i.getCourse().getCompany().getId().equals(currentUserService.getCurrentUserDetails().getCompanyId())))
                .toList();

        if (filteredResults.isEmpty()) {
            throw new EmptyResultDataAccessException("Student is empty");
        }

        return new PageImpl<>(filteredResults, pageable, results.getTotalElements());
    }

    @Override
    public Page<StudentDto> findAll(int page, int size, String sort) {
        Sort sortBy = JPAUtil.getSortRequestParam(sort);
        Pageable pageable = PageRequest.of(page, size, sortBy);
        Page<StudentEntity> results = studentRepository.findAllByDeletedFalse(pageable);
        return filterByCompany(results, pageable).map(studentMapper::toDto);
    }

    @Override
    public Page<StudentDto> search(String name, String phone, String email, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, JPAUtil.getSortRequestParam(sort));
        Specification<StudentEntity> spec = studentSpecification.searchByCriteria(name, phone, email);
        Page<StudentEntity> results = studentRepository.findAll(spec, pageable);
        return filterByCompany(results, pageable).map(studentMapper::toDto);
    }

    @Override
    public Page<StudentDto> filter(String gender, LocalDate birthday, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, JPAUtil.getSortRequestParam(sort));
        Specification<StudentEntity> spec = studentSpecification.filterByCriteria(gender, birthday);
        Page<StudentEntity> results = studentRepository.findAll(spec, pageable);
        if (results.isEmpty()) throw new EmptyResultDataAccessException("No results found");
        return filterByCompany(results, pageable).map(studentMapper::toDto);
    }
}
