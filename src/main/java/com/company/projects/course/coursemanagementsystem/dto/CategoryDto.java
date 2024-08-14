package com.company.projects.course.coursemanagementsystem.dto;

import com.company.projects.course.coursemanagementsystem.repository.CategoryRepository;
import com.company.projects.course.coursemanagementsystem.validation.annotation.Unique;
import com.company.projects.course.coursemanagementsystem.validation.group.ValidationGroups;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.util.Collection;

/**
 * DTO for {@link com.company.projects.course.coursemanagementsystem.model.CategoryEntity}
 */
@Value
@Builder
public class CategoryDto implements Serializable {
    String id;

    @NotBlank(message = ValidationMessage.NAME_NOT_BLACK_MESSAGE,
            groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
    @Size(min = 2, message = ValidationMessage.SIZE_NAME_MESSAGE,
            groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
    @Unique(fieldName = "name", caseSensitive = false, repository = CategoryRepository.class,
            groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
    String name;

    Collection<CourseDto> courses;
}

