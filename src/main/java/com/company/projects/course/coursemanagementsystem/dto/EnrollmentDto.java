package com.company.projects.course.coursemanagementsystem.dto;

import com.company.projects.course.coursemanagementsystem.validation.annotation.BooleanValue;
import com.company.projects.course.coursemanagementsystem.validation.group.ValidationGroups;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.company.projects.course.coursemanagementsystem.model.EnrollmentEntity}
 */
@Value
@Builder
public class EnrollmentDto implements Serializable {
    String id;

    @NotNull(message = "Status must not be null", groups = {ValidationGroups.Common.class})
    @BooleanValue(groups = {ValidationGroups.Common.class})
    Boolean status;

    String note;

    @NotNull(message = "Course must not be null", groups = {ValidationGroups.Common.class})
    @Valid
    CourseDto course;

    @NotNull(message = "Student must not be null", groups = {ValidationGroups.Common.class})
    @Valid
    StudentDto student;

    LocalDate date;
}