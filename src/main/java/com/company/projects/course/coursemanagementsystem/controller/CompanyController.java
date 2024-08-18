package com.company.projects.course.coursemanagementsystem.controller;

import com.company.projects.course.coursemanagementsystem.dto.CompanyDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface CompanyController extends BaseController<String, CompanyDto>{
    ResponseEntity<Page<CompanyDto>> search(String name, String phone, String email, int page, int size, String sort);
}
