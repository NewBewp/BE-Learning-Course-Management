package com.company.projects.course.coursemanagementsystem.controller;

import com.company.projects.course.coursemanagementsystem.dto.AttendanceDto;
import com.company.projects.course.coursemanagementsystem.service.AttendanceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attendances")
public class AttendanceControllerImpl extends BaseControllerImpl<String, AttendanceDto, AttendanceService> implements  AttendanceController {
}
