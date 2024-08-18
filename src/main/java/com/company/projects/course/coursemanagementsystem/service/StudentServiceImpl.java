package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.StudentDto;
import com.company.projects.course.coursemanagementsystem.exception.custom.EmptyResultDataAccessException;
import com.company.projects.course.coursemanagementsystem.mapper.StudentMapper;
import com.company.projects.course.coursemanagementsystem.model.StudentEntity;
import com.company.projects.course.coursemanagementsystem.repository.StudentRepository;
import com.company.projects.course.coursemanagementsystem.repository.specification.StudentSpecification;
import com.company.projects.course.coursemanagementsystem.util.JPAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class StudentServiceImpl extends BaseServiceImpl<String, StudentDto, StudentEntity> implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final StudentSpecification studentSpecification;

    @Autowired
    public StudentServiceImpl(StudentRepository repository, StudentMapper mapper, StudentSpecification studentSpecification) {
        super(repository, mapper, "Student");
        this.studentRepository = repository;
        this.studentMapper = mapper;
        this.studentSpecification = studentSpecification;
    }

    @Override
    public Page<StudentDto> search(String name, String phone, String email, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, JPAUtil.getSortRequestParam(sort));
        Specification<StudentEntity> spec = studentSpecification.searchByCriteria(name, phone, email);
        Page<StudentEntity> results = studentRepository.findAll(spec, pageable);
        if (results.isEmpty()) throw new EmptyResultDataAccessException("Student" + " not found with name = " + name + ", phone = " + phone + ", email = " + email);
        return results.map(studentMapper::toDto);
    }

    @Override
    public Page<StudentDto> filter(String gender, LocalDate birthday, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, JPAUtil.getSortRequestParam(sort));
        Specification<StudentEntity> spec = studentSpecification.filterByCriteria(gender, birthday);
        Page<StudentEntity> results = studentRepository.findAll(spec, pageable);
        if (results.isEmpty()) throw new EmptyResultDataAccessException("No results found");
        return results.map(studentMapper::toDto);
    }
}
