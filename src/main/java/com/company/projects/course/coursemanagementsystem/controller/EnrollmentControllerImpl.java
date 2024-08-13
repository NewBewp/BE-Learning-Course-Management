package com.company.projects.course.coursemanagementsystem.controller;

import com.company.projects.course.coursemanagementsystem.dto.EnrollmentDto;
import com.company.projects.course.coursemanagementsystem.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentControllerImpl extends BaseControllerImpl<String, EnrollmentDto, EnrollmentService> implements EnrollmentController{
    @Autowired
    public EnrollmentControllerImpl(EnrollmentService service) {
        super(service);
    }
}
