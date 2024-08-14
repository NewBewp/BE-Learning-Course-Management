package com.company.projects.course.coursemanagementsystem.dto;

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
    CourseDto course;
    Collection<StudentDto> students;
//    Collection<AttendanceDto> attendances;
//    Collection<AssignmentDto> assignments;
}