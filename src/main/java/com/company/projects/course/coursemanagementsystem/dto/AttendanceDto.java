package com.company.projects.course.coursemanagementsystem.dto;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.company.projects.course.coursemanagementsystem.model.AttendanceEntity}
 */
@Value
@Builder
public class AttendanceDto implements Serializable {
    String id;
    Boolean status;
    String reason;
    ClassroomDto classroom;
    StudentDto student;
    DateDto date;
}