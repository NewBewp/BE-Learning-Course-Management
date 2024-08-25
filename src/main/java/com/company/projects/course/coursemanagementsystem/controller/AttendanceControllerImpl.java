package com.company.projects.course.coursemanagementsystem.controller;

import com.company.projects.course.coursemanagementsystem.dto.AttendanceDto;
import com.company.projects.course.coursemanagementsystem.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/attendances")
@PreAuthorize("hasAnyRole('admin', 'admin_company')")
public class AttendanceControllerImpl extends BaseControllerImpl<String, AttendanceDto, AttendanceService> implements  AttendanceController {
    private final AttendanceService attendanceService;
    @Autowired
    public AttendanceControllerImpl(AttendanceService service) {
        super(service);
        this.attendanceService = service;
    }

    @Override
    @GetMapping("/filter")
    public ResponseEntity<Page<AttendanceDto>> filter(
            @RequestParam(required = false) Boolean status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(required = false) String studentId,
            @RequestParam(required = false) String classroomId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "updatedAt:desc") String sort) {
        Page<AttendanceDto> results = attendanceService.filter(status, date, studentId, classroomId, page, size, sort);
        return ResponseEntity.ok(results);
    }
}
