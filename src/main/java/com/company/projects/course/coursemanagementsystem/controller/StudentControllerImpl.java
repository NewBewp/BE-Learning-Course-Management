package com.company.projects.course.coursemanagementsystem.controller;

import com.company.projects.course.coursemanagementsystem.dto.StudentDto;
import com.company.projects.course.coursemanagementsystem.service.StudentService;
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
@RequestMapping("/students")
@PreAuthorize("hasAnyRole('admin', 'admin_company')")
public class StudentControllerImpl extends BaseControllerImpl<String, StudentDto, StudentService> implements StudentController {
    private final StudentService studentService;
    @Autowired
    public StudentControllerImpl(StudentService service) {
        super(service);
        this.studentService = service;
    }

    @Override
    @GetMapping("/search")
    public ResponseEntity<Page<StudentDto>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "updatedAt:desc") String sort) {
        Page<StudentDto> results = studentService.search(name, phone, email, page, size, sort);
        return ResponseEntity.ok(results);
    }

    @Override
    @GetMapping("/filter")
    public ResponseEntity<Page<StudentDto>> filter(
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthday,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "updatedAt:desc") String sort) {
        Page<StudentDto> results = studentService.filter(gender, birthday, page, size, sort);
        return ResponseEntity.ok(results);
    }
}
