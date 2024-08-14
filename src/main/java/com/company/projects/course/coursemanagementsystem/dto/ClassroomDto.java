package com.company.projects.course.coursemanagementsystem.dto;

import com.company.projects.course.coursemanagementsystem.validation.group.ValidationGroups;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.util.Collection;

/**
 * DTO for {@link com.company.projects.course.coursemanagementsystem.model.ClassroomEntity}
 */
@Value
@Builder
public class ClassroomDto implements Serializable {
    String id;
    String name;

    @NotNull(message = "Course must not be null", groups = {ValidationGroups.Common.class})
    @Valid
    CourseDto course;

    @NotEmpty(message = "Students must not be empty", groups = {ValidationGroups.Common.class})
    @Valid
    Collection<StudentDto> students;
}