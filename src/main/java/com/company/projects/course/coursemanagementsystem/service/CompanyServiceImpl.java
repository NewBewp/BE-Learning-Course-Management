package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.CompanyDto;
import com.company.projects.course.coursemanagementsystem.mapper.CompanyMapper;
import com.company.projects.course.coursemanagementsystem.model.CompanyEntity;
import com.company.projects.course.coursemanagementsystem.repository.CompanyRepository;
import com.company.projects.course.coursemanagementsystem.repository.custom.search.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl extends BaseServiceImpl<String, CompanyDto, CompanyEntity> implements CompanyService {
    @Autowired
    public CompanyServiceImpl(CompanyRepository repository, CompanyMapper mapper, SearchRepository<CompanyEntity, String> searchRepository) {
        super(repository, mapper, "Company", searchRepository);
    }
}
