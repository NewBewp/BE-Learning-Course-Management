package com.company.projects.course.coursemanagementsystem.controller;

import com.company.projects.course.coursemanagementsystem.dto.AccountDto;
import com.company.projects.course.coursemanagementsystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@PreAuthorize("hasRole('admin')")
public class AccountControllerImpl extends BaseControllerImpl<String, AccountDto, AccountService> implements AccountController{
    @Autowired
    public AccountControllerImpl(AccountService service) {
         super(service);
    }
}

