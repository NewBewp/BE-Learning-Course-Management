package com.company.projects.course.coursemanagementsystem.dto;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

/**
 * DTO for {@link com.company.projects.course.coursemanagementsystem.model.UserEntity}
 */
@Value
@Builder
public class UserDto implements Serializable {
    String id;
    String name;
    String gender;
    LocalDate birthday;
    String phone;
    String email;
    String address;
    Collection<AssignmentDto> assignments;
    Collection<AccountDto> accounts;
}