package com.company.projects.course.coursemanagementsystem.dto;

import com.company.projects.course.coursemanagementsystem.repository.CompanyRepository;
import com.company.projects.course.coursemanagementsystem.repository.StudentRepository;
import com.company.projects.course.coursemanagementsystem.validation.annotation.Unique;
import com.company.projects.course.coursemanagementsystem.validation.group.ValidationGroups;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.company.projects.course.coursemanagementsystem.model.StudentEntity}
 */
@Value
@Builder
public class StudentDto implements Serializable {
    String id;

    @NotBlank(message = ValidationMessage.NAME_NOT_BLACK_MESSAGE, groups = {ValidationGroups.Common.class})
    @Size(min = 2, message = ValidationMessage.SIZE_NAME_MESSAGE, groups = {ValidationGroups.Common.class})
    String name;

    @NotBlank(message = "Gender must not be null", groups = {ValidationGroups.Common.class})
    @Pattern(regexp = "male|female|other", message = "Gender must be one of the following: male, female, other", groups = {ValidationGroups.Common.class})
    String gender;

    @NotNull(message = "Birthday must not be null", groups = {ValidationGroups.Common.class})
    @Past(message = "Birthday must start in the past")
    LocalDate birthday;

    @NotBlank(message = ValidationMessage.PHONE_NOT_BLACK_MESSAGE, groups = {ValidationGroups.Common.class})
    @Size(min = 10, max = 11, message = ValidationMessage.SIZE_PHONE_MESSAGE, groups = {ValidationGroups.Common.class})
    @Pattern(regexp = ValidationMessage.PATTERN_PHONE, message = ValidationMessage.PATTERN_MESSAGE, groups = {ValidationGroups.Common.class})
    @Unique(fieldName = "name", caseSensitive = false, repository = StudentRepository.class, groups = {ValidationGroups.Common.class})
    String phone;

    @NotBlank(message = ValidationMessage.EMAIL_NOT_BLACK_MESSAGE, groups = {ValidationGroups.Common.class})
    @Size(min = 3, max = 254, message = ValidationMessage.SIZE_EMAIL_MESSAGE, groups = {ValidationGroups.Common.class})
    @Email(regexp = ValidationMessage.PATTERN_EMAIL, message = ValidationMessage.EMAIL_VALID_MESSAGE, groups = {ValidationGroups.Common.class})
    @Pattern(regexp = ValidationMessage.PATTERN_EMAIL, message = ValidationMessage.EMAIL_VALID_MESSAGE, groups = {ValidationGroups.Common.class})
    @Unique(fieldName = "email", caseSensitive = false, repository = StudentRepository.class, groups = ValidationGroups.Common.class)
    String email;

    @NotBlank(message = ValidationMessage.ADDRESS_NOT_BLACK_MESSAGE, groups = {ValidationGroups.Common.class})
    @Size(min = 10, message = ValidationMessage.SIZE_ADDRESS_MESSAGE, groups = {ValidationGroups.Common.class})
    String address;
}