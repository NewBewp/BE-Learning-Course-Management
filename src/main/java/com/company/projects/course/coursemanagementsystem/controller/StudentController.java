package com.company.projects.course.coursemanagementsystem.controller;

import com.company.projects.course.coursemanagementsystem.dto.CompanyDto;
import com.company.projects.course.coursemanagementsystem.dto.StudentDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface StudentController extends BaseController<String, StudentDto>{
    ResponseEntity<Page<StudentDto>> search(String name, String phone, String email, int page, int size, String sort);
    ResponseEntity<Page<StudentDto>> filter(String gender, LocalDate birthday, int page, int size, String sort);
}
