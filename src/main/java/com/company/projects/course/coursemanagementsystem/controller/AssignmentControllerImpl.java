package com.company.projects.course.coursemanagementsystem.controller;

import com.company.projects.course.coursemanagementsystem.dto.AssignmentDto;
import com.company.projects.course.coursemanagementsystem.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assignments")
public class  AssignmentControllerImpl extends BaseControllerImpl<String, AssignmentDto, AssignmentService> implements AssignmentController {
    @Autowired
    public AssignmentControllerImpl(AssignmentService service) {
        super(service);
    }
}
