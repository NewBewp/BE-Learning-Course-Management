package com.company.projects.course.coursemanagementsystem.controller;

import com.company.projects.course.coursemanagementsystem.dto.CompanyDto;
import com.company.projects.course.coursemanagementsystem.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies")
@PreAuthorize("hasRole('admin')")
public class CompanyControllerImpl extends BaseControllerImpl<String, CompanyDto, CompanyService> implements CompanyController {
    private final CompanyService companyService;
    @Autowired
    public CompanyControllerImpl(CompanyService service) {
        super(service);
        this.companyService = service;
    }

    @Override
    @GetMapping("/search")
    public ResponseEntity<Page<CompanyDto>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "updatedAt:desc") String sort) {
        Page<CompanyDto> results = companyService.search(name, phone, email, page, size, sort);
        return ResponseEntity.ok(results);
    }
}
