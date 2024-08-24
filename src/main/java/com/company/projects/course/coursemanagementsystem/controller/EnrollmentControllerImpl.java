package com.company.projects.course.coursemanagementsystem.controller;

import com.company.projects.course.coursemanagementsystem.dto.CourseDto;
import com.company.projects.course.coursemanagementsystem.dto.EnrollmentDto;
import com.company.projects.course.coursemanagementsystem.dto.StudentDto;
import com.company.projects.course.coursemanagementsystem.service.CourseService;
import com.company.projects.course.coursemanagementsystem.service.EmailService;
import com.company.projects.course.coursemanagementsystem.service.EnrollmentService;
import com.company.projects.course.coursemanagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentControllerImpl extends BaseControllerImpl<String, EnrollmentDto, EnrollmentService> implements EnrollmentController{
    private final EnrollmentService enrollmentService;
    private final StudentService studentService;
    private final EmailService emailService;
    private final CourseService courseService;
    @Autowired
    public EnrollmentControllerImpl(EnrollmentService service, StudentService studentService, EmailService emailService, CourseService courseService) {
        super(service);
        this.enrollmentService = service;
        this.studentService = studentService;
        this.emailService = emailService;
        this.courseService = courseService;
    }

    @Override
    @PostMapping
    public ResponseEntity<EnrollmentDto> create(@RequestBody EnrollmentDto enrollmentDto) {
        StudentDto studentDto = studentService.save(enrollmentDto.getStudent());
        enrollmentDto.setStudent(studentDto);
        enrollmentDto.setStatus("PENDING");
        EnrollmentDto createdDto = service.save(enrollmentDto);
        CourseDto courseDto = courseService.findById(createdDto.getCourse().getId());
        try {
            emailService.sendThanksEnrollment(studentDto.getEmail(), studentDto, courseDto, courseDto.getCompany());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
    }

    @Override
    @GetMapping("/filter")
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
