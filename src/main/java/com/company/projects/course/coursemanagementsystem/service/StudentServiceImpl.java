package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.StudentDto;
import com.company.projects.course.coursemanagementsystem.mapper.StudentMapper;
import com.company.projects.course.coursemanagementsystem.model.StudentEntity;
import com.company.projects.course.coursemanagementsystem.repository.StudentRepository;
import com.company.projects.course.coursemanagementsystem.repository.custom.search.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends BaseServiceImpl<String, StudentDto, StudentEntity> implements StudentService {
    @Autowired
    public StudentServiceImpl(StudentRepository repository, StudentMapper mapper, SearchRepository<StudentEntity, String> searchRepository) {
        super(repository, mapper, "Student", searchRepository);
    }
}
