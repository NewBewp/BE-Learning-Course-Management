package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.AttendanceDto;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface AttendanceService extends BaseService<String, AttendanceDto> {
    Page<AttendanceDto> filter(Boolean status, LocalDate date, String studentId, String classroomId, int page, int size, String sort);
}
