package com.company.projects.course.coursemanagementsystem.dto;

import com.company.projects.course.coursemanagementsystem.validation.group.ValidationGroups;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.company.projects.course.coursemanagementsystem.model.CourseEntity}
 */
@Value
@Builder
public class CourseDto implements Serializable {
    String id;

    @NotBlank(message = ValidationMessage.NAME_NOT_BLACK_MESSAGE, groups = {ValidationGroups.Common.class})
    @Size(min = 2, message = ValidationMessage.SIZE_NAME_MESSAGE, groups = {ValidationGroups.Common.class})
    String name;

    @NotBlank(message = "Description must not be null", groups = {ValidationGroups.Common.class})
    @Size(min = 2, message = "Description must have at lease 2 characters", groups = {ValidationGroups.Common.class})
    String description;

    @NotNull(message = "Start date must be not null", groups = {ValidationGroups.Common.class})
    @Future(message = "Start date must be in the future")
    LocalDate startDate;

    @NotNull(message = "End date must be not null", groups = {ValidationGroups.Common.class})
    @Future(message = "End date must be in the future")
    LocalDate endDate;

    @NotNull(message = "Category must not be null", groups = {ValidationGroups.Common.class})
    @Valid
    CategoryDto category;

    @NotNull(message = "Company must not be null", groups = {ValidationGroups.Common.class})
    @Valid
    CompanyDto company;
}