package com.company.projects.course.coursemanagementsystem.controller;

import com.company.projects.course.coursemanagementsystem.dto.EnrollmentDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface EnrollmentController extends BaseController<String, EnrollmentDto>{
    ResponseEntity<Page<EnrollmentDto>> filter(String status, LocalDate date, String courseId, String studentId, int page, int size, String sort);
}
