package com.company.projects.course.coursemanagementsystem.dto;

import com.company.projects.course.coursemanagementsystem.validation.group.ValidationGroups;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "Note must not be blank", groups = {ValidationGroups.Common.class})
    @Size(min = 10, message = "Note must have at lease 10 characters")
    String note;
    @NotNull
    ClassroomDto classroom;
    UserDto user;
}