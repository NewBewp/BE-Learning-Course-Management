package com.company.projects.course.coursemanagementsystem.dto;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

/**
 * DTO for {@link com.company.projects.course.coursemanagementsystem.model.CourseEntity}
 */
@Value
@Builder
public class CourseDto implements Serializable {
    String id;
    String name;
    String description;
    LocalDate startDate;
    LocalDate endDate;
    CategoryDto category;
    CompanyDto company;
    Collection<EnrollmentDto> enrollments;
    Collection<ClassroomDto> classrooms;
}