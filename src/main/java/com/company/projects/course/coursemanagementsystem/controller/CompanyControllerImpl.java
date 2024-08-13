package com.company.projects.course.coursemanagementsystem.controller;

import com.company.projects.course.coursemanagementsystem.dto.CompanyDto;
import com.company.projects.course.coursemanagementsystem.service.ClassroomService;
import com.company.projects.course.coursemanagementsystem.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies")
public class CompanyControllerImpl extends BaseControllerImpl<String, CompanyDto, CompanyService> implements CompanyController {
    @Autowired
    public CompanyControllerImpl(CompanyService service) {
        super(service);
    }
}
