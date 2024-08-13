package com.company.projects.course.coursemanagementsystem.controller;

import com.company.projects.course.coursemanagementsystem.dto.RoleDto;
import com.company.projects.course.coursemanagementsystem.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleControllerImpl extends BaseControllerImpl<String, RoleDto, RoleService> implements RoleController {
    @Autowired
    public RoleControllerImpl(RoleService service) {
        super(service);
    }
}
