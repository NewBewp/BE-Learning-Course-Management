package com.company.projects.course.coursemanagementsystem.controller;

import com.company.projects.course.coursemanagementsystem.dto.AttendanceDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface AttendanceController extends BaseController<String, AttendanceDto> {
    ResponseEntity<Page<AttendanceDto>> filter(Boolean status, LocalDate date, String studentId, String classroomId, int page, int size, String sort);
}
