package com.company.projects.course.coursemanagementsystem.dto;

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
    String name;
    String phone;
    String email;
    String address;
    Collection<CourseDto> courses;
}