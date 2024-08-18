package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.StudentDto;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface StudentService extends BaseService<String, StudentDto> {
    Page<StudentDto> search(String name, String phone, String email, int page, int size, String sort);
    Page<StudentDto> filter(String gender, LocalDate birthday, int page, int size, String sort);
}