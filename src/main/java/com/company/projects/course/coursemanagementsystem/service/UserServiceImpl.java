package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.UserDto;
import com.company.projects.course.coursemanagementsystem.exception.custom.EmptyResultDataAccessException;
import com.company.projects.course.coursemanagementsystem.mapper.UserMapper;
import com.company.projects.course.coursemanagementsystem.model.ClassroomEntity;
import com.company.projects.course.coursemanagementsystem.model.UserEntity;
import com.company.projects.course.coursemanagementsystem.repository.UserRepository;
import com.company.projects.course.coursemanagementsystem.repository.specification.UserSpecification;
import com.company.projects.course.coursemanagementsystem.util.JPAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl extends BaseServiceImpl<String, UserDto, UserEntity> implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserSpecification userSpecification;
    private final CurrentUserService currentUserService;

    @Autowired
    public UserServiceImpl(UserRepository repository, UserMapper mapper, UserSpecification userSpecification, CurrentUserService currentUserService) {
        super(repository, mapper, "User");
        this.userRepository = repository;
        this.userMapper = mapper;
        this.userSpecification = userSpecification;
        this.currentUserService = currentUserService;
    }

    public Page<UserEntity> filterByCompany(Page<UserEntity> results, Pageable pageable) {
        if (currentUserService.getCurrentUserRole().equals("ROLE_admin")) return results;
        List<UserEntity> filteredResults = results.stream()
                .filter(e -> e.getCompany().getId().equals(currentUserService.getCurrentUserDetails().getCompanyId())).toList();

        if (filteredResults.isEmpty()) {
            throw new EmptyResultDataAccessException("User is empty");
        }

        return new PageImpl<>(filteredResults, pageable, results.getTotalElements());
    }

    @Override
    public Page<UserDto> findAll(int page, int size, String sort) {
        Sort sortBy = JPAUtil.getSortRequestParam(sort);
        Pageable pageable = PageRequest.of(page, size, sortBy);
        Page<UserEntity> results = userRepository.findAllByDeletedFalse(pageable);
        if (results.isEmpty()) throw new EmptyResultDataAccessException("User" + " is empty");
        return filterByCompany(results, pageable).map(userMapper::toDto);
    }

    @Override
    public Page<UserDto> search(String name, String phone, String email, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, JPAUtil.getSortRequestParam(sort));
        Specification<UserEntity> spec = userSpecification.searchByCriteria(name, phone, email);
        Page<UserEntity> results = userRepository.findAll(spec, pageable);
        if (results.isEmpty()) throw new EmptyResultDataAccessException("User" + " not found with name = " + name + ", phone = " + phone + ", email = " + email);
        return filterByCompany(results, pageable).map(userMapper::toDto);
    }

    @Override
    public Page<UserDto> filter(String gender, LocalDate birthday, String companyId, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, JPAUtil.getSortRequestParam(sort));
        Specification<UserEntity> spec = userSpecification.filterByCriteria(gender, birthday);
        Page<UserEntity> results = userRepository.findAll(spec, pageable);
        if (results.isEmpty()) throw new EmptyResultDataAccessException("No results found");
        return filterByCompany(results, pageable).map(userMapper::toDto);
    }
}

