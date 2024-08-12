package com.company.projects.course.coursemanagementsystem.dto;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.company.projects.course.coursemanagementsystem.model.AssignmentEntity}
 */
@Value
@Builder
public class AssignmentDto implements Serializable {
    String id;
    String note;
    ClassroomDto classroom;
    UserDto user;
}