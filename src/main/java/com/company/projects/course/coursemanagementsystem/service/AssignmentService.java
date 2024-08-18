package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.AssignmentDto;
import org.springframework.data.domain.Page;

public interface AssignmentService extends BaseService<String, AssignmentDto> {
    Page<AssignmentDto> filter(String userId, String classroomId, int page, int size, String sort);
}
