package com.company.projects.course.coursemanagementsystem.controller;

import com.company.projects.course.coursemanagementsystem.dto.EnrollmentDto;
import com.company.projects.course.coursemanagementsystem.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentControllerImpl extends BaseControllerImpl<String, EnrollmentDto, EnrollmentService> implements EnrollmentController{
    private final EnrollmentService enrollmentService;
    @Autowired
    public EnrollmentControllerImpl(EnrollmentService service) {
        super(service);
        this.enrollmentService = service;
    }

    @Override
    public ResponseEntity<Page<EnrollmentDto>> filter(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(required = false) String courseId,
            @RequestParam(required = false) String studentId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "updatedAt:desc") String sort) {
        Page<EnrollmentDto> results = enrollmentService.filter(status, date, courseId, studentId, page, size, sort);
        return ResponseEntity.ok(results);
    }
}
