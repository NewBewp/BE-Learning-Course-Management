package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.StudentDto;
import com.company.projects.course.coursemanagementsystem.exception.custom.EmptyResultDataAccessException;
import com.company.projects.course.coursemanagementsystem.mapper.StudentMapper;
import com.company.projects.course.coursemanagementsystem.model.StudentEntity;
import com.company.projects.course.coursemanagementsystem.repository.StudentRepository;
import com.company.projects.course.coursemanagementsystem.service.custom.search.EmailService;
import com.company.projects.course.coursemanagementsystem.service.custom.search.NameService;
import com.company.projects.course.coursemanagementsystem.service.custom.search.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StudentServiceImpl extends BaseServiceImpl<String, StudentDto, StudentEntity> implements StudentService, NameService<StudentDto>, PhoneService<StudentDto>, EmailService<StudentDto> {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Autowired
    public StudentServiceImpl(StudentRepository repository, StudentMapper mapper) {
        super(repository, mapper, "Student");
        this.studentRepository = repository;
        this.studentMapper = mapper;
    }

    @Override
    public Collection<StudentDto> searchAllByName(String name) {
        Collection<StudentEntity> results = studentRepository.findAllByNameAndDeletedFalse(name);
        if (results.isEmpty()) throw new EmptyResultDataAccessException("Student" + " not found with name = " + name);
        return results.stream().map(studentMapper::toDto).toList();
    }

    @Override
    public Collection<StudentDto> searchAllByPhone(String phone) {
        Collection<StudentEntity> results = studentRepository.findAllByPhoneAndDeletedFalse(phone);
        if (results.isEmpty()) throw new EmptyResultDataAccessException("Student" + " not found with phone = " + phone);
        return results.stream().map(studentMapper::toDto).toList();
    }

    @Override
    public Collection<StudentDto> searchAllByEmail(String email) {
        Collection<StudentEntity> results = studentRepository.findAllByEmailAndDeletedFalse(email);
        if (results.isEmpty()) throw new EmptyResultDataAccessException("Student" + " not found with email = " + email);
        return results.stream().map(studentMapper::toDto).toList();
    }
}
