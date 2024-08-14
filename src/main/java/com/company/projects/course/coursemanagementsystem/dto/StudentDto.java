package com.company.projects.course.coursemanagementsystem.dto;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

/**
 * DTO for {@link com.company.projects.course.coursemanagementsystem.model.StudentEntity}
 */
@Value
@Builder
public class StudentDto implements Serializable {
    String id;
    String name;
    String gender;
    LocalDate birthday;
    String phone;
    String email;
    String address;
//    Collection<EnrollmentDto> enrollments;
//    Collection<ClassroomDto> classrooms;
//    Collection<AttendanceDto> attendances;
}