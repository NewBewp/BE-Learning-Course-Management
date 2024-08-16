package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.CompanyDto;
import com.company.projects.course.coursemanagementsystem.exception.custom.EmptyResultDataAccessException;
import com.company.projects.course.coursemanagementsystem.mapper.CompanyMapper;
import com.company.projects.course.coursemanagementsystem.model.CompanyEntity;
import com.company.projects.course.coursemanagementsystem.repository.CompanyRepository;
import com.company.projects.course.coursemanagementsystem.service.custom.search.EmailService;
import com.company.projects.course.coursemanagementsystem.service.custom.search.NameService;
import com.company.projects.course.coursemanagementsystem.service.custom.search.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CompanyServiceImpl extends BaseServiceImpl<String, CompanyDto, CompanyEntity> implements CompanyService, NameService<CompanyDto>, EmailService<CompanyDto>, PhoneService<CompanyDto> {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Autowired
    public CompanyServiceImpl(CompanyRepository repository, CompanyMapper mapper) {
        super(repository, mapper, "Company");
        this.companyMapper = mapper;
        this.companyRepository = repository;
    }

    @Override
    public Collection<CompanyDto> searchAllByName(String name) {
        Collection<CompanyEntity> results = companyRepository.findAllByNameAndDeletedFalse(name);
        if (results.isEmpty()) throw new EmptyResultDataAccessException("Company" + " not found with name = " + name);
        return results.stream().map(companyMapper::toDto).toList();
    }

    @Override
    public Collection<CompanyDto> searchAllByPhone(String phone) {
        Collection<CompanyEntity> results = companyRepository.findAllByPhoneAndDeletedFalse(phone);
        if (results.isEmpty()) throw new EmptyResultDataAccessException("Company" + " not found with phone = " + phone);
        return results.stream().map(companyMapper::toDto).toList();
    }

    @Override
    public Collection<CompanyDto> searchAllByEmail(String email) {
        Collection<CompanyEntity> results = companyRepository.findAllByEmailAndDeletedFalse(email);
        if (results.isEmpty()) throw new EmptyResultDataAccessException("Company" + " not found with email = " + email);
        return results.stream().map(companyMapper::toDto).toList();
    }
}
