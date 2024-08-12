package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.CompanyDto;
import com.company.projects.course.coursemanagementsystem.mapper.CompanyMapper;
import com.company.projects.course.coursemanagementsystem.model.CompanyEntity;
import com.company.projects.course.coursemanagementsystem.repository.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl extends BaseServiceImpl<String, CompanyDto, CompanyEntity> implements CompanyService {
    public CompanyServiceImpl(CompanyRepository repository, CompanyMapper mapper) {
        super(repository, mapper, "Company");
    }
}
