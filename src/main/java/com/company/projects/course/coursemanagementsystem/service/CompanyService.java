package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.CompanyDto;
import org.springframework.data.domain.Page;

public interface CompanyService extends BaseService<String, CompanyDto> {
    Page<CompanyDto> search (String name, String phone, String email, int page, int size, String sort);
}
