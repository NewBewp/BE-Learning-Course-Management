package com.company.projects.course.coursemanagementsystem.controller;

import com.company.projects.course.coursemanagementsystem.dto.StudentDto;
import com.company.projects.course.coursemanagementsystem.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface UserController extends BaseController<String, UserDto>{
    ResponseEntity<Page<UserDto>> search(String name, String phone, String email, int page, int size, String sort);
    ResponseEntity<Page<UserDto>> filter(String gender, LocalDate birthday, String companyId, int page, int size, String sort);
}
