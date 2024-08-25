package com.company.projects.course.coursemanagementsystem.controller;

import com.company.projects.course.coursemanagementsystem.dto.AccountDto;
import com.company.projects.course.coursemanagementsystem.dto.RoleDto;
import com.company.projects.course.coursemanagementsystem.dto.UserDto;
import com.company.projects.course.coursemanagementsystem.service.AccountService;
import com.company.projects.course.coursemanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasRole('admin')")
public class UserControllerImpl extends BaseControllerImpl<String, UserDto, UserService> implements UserController{
    private final UserService userService;
    private final AccountService accountService;
    @Autowired
    public UserControllerImpl(UserService service, AccountService accountService) {
        super(service);
        this.userService = service;
        this.accountService = accountService;
    }

    @Override
    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        RoleDto roleDto = userDto.getRole();
        UserDto createdDto = userService.save(userDto);
        AccountDto accountDto = AccountDto.builder().user(createdDto).role(roleDto).build();
        accountService.save(accountDto);
        return ResponseEntity.ok(createdDto);
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
            @RequestParam(required = false) String companyId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "updatedAt:desc") String sort) {
        Page<UserDto> results = userService.filter(gender, birthday, companyId, page, size, sort);
        return ResponseEntity.ok(results);
    }
}

