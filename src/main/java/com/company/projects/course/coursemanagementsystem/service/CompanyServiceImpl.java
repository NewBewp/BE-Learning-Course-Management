package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.CompanyDto;
import com.company.projects.course.coursemanagementsystem.exception.custom.EmptyResultDataAccessException;
import com.company.projects.course.coursemanagementsystem.mapper.CompanyMapper;
import com.company.projects.course.coursemanagementsystem.model.CompanyEntity;
import com.company.projects.course.coursemanagementsystem.repository.CompanyRepository;
import com.company.projects.course.coursemanagementsystem.repository.specification.CompanySpecification;
import com.company.projects.course.coursemanagementsystem.repository.specification.common.NPESpecification;
import com.company.projects.course.coursemanagementsystem.util.JPAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl extends BaseServiceImpl<String, CompanyDto, CompanyEntity> implements CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final CompanySpecification companySpecification;

    @Autowired
    public CompanyServiceImpl(CompanyRepository repository, CompanyMapper mapper, CompanySpecification companySpecification) {
        super(repository, mapper, "Company");
        this.companyMapper = mapper;
        this.companyRepository = repository;
        this.companySpecification = companySpecification;
    }

    @Override
    public Page<CompanyDto> search(String name, String phone, String email, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, JPAUtil.getSortRequestParam(sort));
        Specification<CompanyEntity> spec = companySpecification.searchByCriteria(name, phone, email);
        Page<CompanyEntity> results = companyRepository.findAll(spec, pageable);
        if (results.isEmpty()) throw new EmptyResultDataAccessException("Company" + " not found with name = " + name + ", phone = " + phone + ", email = " + email);
        return results.map(companyMapper::toDto);
    }
}
