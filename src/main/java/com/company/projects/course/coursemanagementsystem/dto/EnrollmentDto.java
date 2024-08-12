package com.company.projects.course.coursemanagementsystem.dto;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.company.projects.course.coursemanagementsystem.model.EnrollmentEntity}
 */
@Value
@Builder
public class EnrollmentDto implements Serializable {
    String id;
    Boolean status;
    String note;
    CourseDto course;
    StudentDto student;
    DateDto date;
}