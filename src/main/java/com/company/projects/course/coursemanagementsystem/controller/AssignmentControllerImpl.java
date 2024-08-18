package com.company.projects.course.coursemanagementsystem.controller;

import com.company.projects.course.coursemanagementsystem.dto.AssignmentDto;
import com.company.projects.course.coursemanagementsystem.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assignments")
public class  AssignmentControllerImpl extends BaseControllerImpl<String, AssignmentDto, AssignmentService> implements AssignmentController {
    private final AssignmentService assignmentService;
    @Autowired
    public AssignmentControllerImpl(AssignmentService service) {
        super(service);
        this.assignmentService = service;
    }

    @Override
    @GetMapping("/filter")
    public ResponseEntity<Page<AssignmentDto>> filter(
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String classroomId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "updatedAt:desc") String sort) {
        Page<AssignmentDto> results = assignmentService.filter(userId, classroomId, page, size, sort);
        return ResponseEntity.ok(results);
    }
}
