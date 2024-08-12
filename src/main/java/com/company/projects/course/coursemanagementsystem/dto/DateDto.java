package com.company.projects.course.coursemanagementsystem.dto;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

/**
 * DTO for {@link com.company.projects.course.coursemanagementsystem.model.DateEntity}
 */
@Value
@Builder
public class DateDto implements Serializable {
    LocalDate date;
    Collection<EnrollmentDto> enrollments;
    Collection<AttendanceDto> attendances;
}