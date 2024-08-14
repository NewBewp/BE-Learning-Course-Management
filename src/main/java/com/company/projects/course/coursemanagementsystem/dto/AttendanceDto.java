package com.company.projects.course.coursemanagementsystem.dto;

import com.company.projects.course.coursemanagementsystem.validation.annotation.BooleanValue;
import com.company.projects.course.coursemanagementsystem.validation.group.ValidationGroups;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.company.projects.course.coursemanagementsystem.model.AttendanceEntity}
 */
@Value
@Builder
public class AttendanceDto implements Serializable {
    String id;

    @NotNull(message = "Status must not be null", groups = {ValidationGroups.Common.class})
    @BooleanValue(groups = {ValidationGroups.Common.class})
    Boolean status;

    String reason;

    @NotNull(message = "Classroom must not be null", groups = {ValidationGroups.Common.class})
    @Valid
    ClassroomDto classroom;

    @NotNull(message = "Student must not be null", groups = {ValidationGroups.Common.class})
    @Valid
    StudentDto student;

    LocalDate date;
}