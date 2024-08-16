package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.UserDto;
import com.company.projects.course.coursemanagementsystem.exception.custom.EmptyResultDataAccessException;
import com.company.projects.course.coursemanagementsystem.mapper.UserMapper;
import com.company.projects.course.coursemanagementsystem.model.UserEntity;
import com.company.projects.course.coursemanagementsystem.repository.UserRepository;
import com.company.projects.course.coursemanagementsystem.service.custom.search.EmailService;
import com.company.projects.course.coursemanagementsystem.service.custom.search.NameService;
import com.company.projects.course.coursemanagementsystem.service.custom.search.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServiceImpl extends BaseServiceImpl<String, UserDto, UserEntity> implements UserService, NameService<UserDto>, PhoneService<UserDto>, EmailService<UserDto> {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository repository, UserMapper mapper) {
        super(repository, mapper, "User");
        this.userRepository = repository;
        this.userMapper = mapper;
    }

    @Override
    public Collection<UserDto> searchAllByName(String name) {
        Collection<UserEntity> results = userRepository.findAllByNameAndDeletedFalse(name);
        if (results.isEmpty()) throw new EmptyResultDataAccessException("User" + " not found with name = " + name);
        return results.stream().map(userMapper::toDto).toList();
    }

    @Override
    public Collection<UserDto> searchAllByPhone(String phone) {
        Collection<UserEntity> results = userRepository.findAllByPhoneAndDeletedFalse(phone);
        if (results.isEmpty()) throw new EmptyResultDataAccessException("User" + " not found with phone = " + phone);
        return results.stream().map(userMapper::toDto).toList();
    }

    @Override
    public Collection<UserDto> searchAllByEmail(String email) {
        Collection<UserEntity> results = userRepository.findAllByEmailAndDeletedFalse(email);
        if (results.isEmpty()) throw new EmptyResultDataAccessException("User" + " not found with email = " + email);
        return results.stream().map(userMapper::toDto).toList();
    }
}

