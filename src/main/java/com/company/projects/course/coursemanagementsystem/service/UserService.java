package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.StudentDto;
import com.company.projects.course.coursemanagementsystem.dto.UserDto;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface UserService extends BaseService<String, UserDto> {
    Page<UserDto> search(String name, String phone, String email, int page, int size, String sort);
    Page<UserDto> filter(String gender, LocalDate birthday, String companyId, int page, int size, String sort);
}
