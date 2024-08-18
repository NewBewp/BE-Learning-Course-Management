package com.company.projects.course.coursemanagementsystem.controller;

import com.company.projects.course.coursemanagementsystem.dto.StudentDto;
import com.company.projects.course.coursemanagementsystem.dto.UserDto;
import com.company.projects.course.coursemanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/users")
public class UserControllerImpl extends BaseControllerImpl<String, UserDto, UserService> implements UserController{
    private final UserService userService;
    @Autowired
    public UserControllerImpl(UserService service) {
        super(service);
        this.userService = service;
    }

    @Override
    @GetMapping("/search")
    public ResponseEntity<Page<UserDto>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "updatedAt:desc") String sort) {
        Page<UserDto> results = userService.search(name, phone, email, page, size, sort);
        return ResponseEntity.ok(results);
    }

    @Override
    @GetMapping("/filter")
    public ResponseEntity<Page<UserDto>> filter(
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthday,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "updatedAt:desc") String sort) {
        Page<UserDto> results = userService.filter(gender, birthday, page, size, sort);
        return ResponseEntity.ok(results);
    }
}
