package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.UserDto;
import com.company.projects.course.coursemanagementsystem.exception.custom.EmptyResultDataAccessException;
import com.company.projects.course.coursemanagementsystem.mapper.UserMapper;
import com.company.projects.course.coursemanagementsystem.model.UserEntity;
import com.company.projects.course.coursemanagementsystem.repository.UserRepository;
import com.company.projects.course.coursemanagementsystem.repository.specification.UserSpecification;
import com.company.projects.course.coursemanagementsystem.util.JPAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserServiceImpl extends BaseServiceImpl<String, UserDto, UserEntity> implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserSpecification userSpecification;

    @Autowired
    public UserServiceImpl(UserRepository repository, UserMapper mapper, UserSpecification userSpecification) {
        super(repository, mapper, "User");
        this.userRepository = repository;
        this.userMapper = mapper;
        this.userSpecification = userSpecification;
    }

    @Override
    public Page<UserDto> search(String name, String phone, String email, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, JPAUtil.getSortRequestParam(sort));
        Specification<UserEntity> spec = userSpecification.searchByCriteria(name, phone, email);
        Page<UserEntity> results = userRepository.findAll(spec, pageable);
        if (results.isEmpty()) throw new EmptyResultDataAccessException("User" + " not found with name = " + name + ", phone = " + phone + ", email = " + email);
        return results.map(userMapper::toDto);
    }

    @Override
    public Page<UserDto> filter(String gender, LocalDate birthday, String companyId, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, JPAUtil.getSortRequestParam(sort));
        Specification<UserEntity> spec = userSpecification.filterByCriteria(gender, birthday);
        Page<UserEntity> results = userRepository.findAll(spec, pageable);
        if (results.isEmpty()) throw new EmptyResultDataAccessException("No results found");
        return results.map(userMapper::toDto);
    }
}

