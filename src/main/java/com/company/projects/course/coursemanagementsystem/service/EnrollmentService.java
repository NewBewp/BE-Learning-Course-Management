package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.EnrollmentDto;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface EnrollmentService extends BaseService<String, EnrollmentDto> {
    Page<EnrollmentDto> filter(String status, LocalDate date, String courseId, String studentId, int page, int size, String sort);
}
