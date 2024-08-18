package com.company.projects.course.coursemanagementsystem.controller;

import com.company.projects.course.coursemanagementsystem.dto.AssignmentDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface AssignmentController extends BaseController<String, AssignmentDto> {
    ResponseEntity<Page<AssignmentDto>> filter(String userId, String classroomId, int page, int size, String sort);
}
