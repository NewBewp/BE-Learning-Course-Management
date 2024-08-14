package com.company.projects.course.coursemanagementsystem.dto;

import com.company.projects.course.coursemanagementsystem.repository.CategoryRepository;
import com.company.projects.course.coursemanagementsystem.repository.CompanyRepository;
import com.company.projects.course.coursemanagementsystem.validation.annotation.Unique;
import com.company.projects.course.coursemanagementsystem.validation.group.ValidationGroups;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.util.Collection;

/**
 * DTO for {@link com.company.projects.course.coursemanagementsystem.model.CompanyEntity}
 */
@Value
@Builder
public class CompanyDto implements Serializable {
    String id;
    @NotBlank(message = ValidationMessage.NAME_NOT_BLACK_MESSAGE, groups = {ValidationGroups.Common.class})
    @Size(min = 2, message = ValidationMessage.SIZE_NAME_MESSAGE, groups = {ValidationGroups.Common.class})
    String name;


    @NotBlank(message = ValidationMessage.PHONE_NOT_BLACK_MESSAGE, groups = {ValidationGroups.Common.class})
    @Size(min = 10, max = 11, message = ValidationMessage.SIZE_PHONE_MESSAGE, groups = {ValidationGroups.Common.class})
    @Pattern(regexp = ValidationMessage.PATTERN_PHONE, message = ValidationMessage.PATTERN_MESSAGE, groups = {ValidationGroups.Common.class})
    @Unique(fieldName = "name", caseSensitive = false, repository = CompanyRepository.class, groups = {ValidationGroups.Common.class})
    String phone;

    @NotBlank(message = ValidationMessage.EMAIL_NOT_BLACK_MESSAGE, groups = {ValidationGroups.Common.class})
    @Size(min = 3, max = 254, message = ValidationMessage.SIZE_EMAIL_MESSAGE, groups = {ValidationGroups.Common.class})
    @Email(regexp = ValidationMessage.PATTERN_EMAIL, message = ValidationMessage.EMAIL_VALID_MESSAGE, groups = {ValidationGroups.Common.class})
    @Pattern(regexp = ValidationMessage.PATTERN_EMAIL, message = ValidationMessage.EMAIL_VALID_MESSAGE, groups = {ValidationGroups.Common.class})
    @Unique(fieldName = "email", caseSensitive = false, repository = CompanyRepository.class, groups = ValidationGroups.Common.class)
    String email;

    @NotBlank(message = ValidationMessage.ADDRESS_NOT_BLACK_MESSAGE, groups = {ValidationGroups.Common.class})
    @Size(min = 10, message = ValidationMessage.SIZE_ADDRESS_MESSAGE, groups = {ValidationGroups.Common.class})
    String address;
}