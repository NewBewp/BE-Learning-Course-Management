package com.company.projects.course.coursemanagementsystem.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class CreateClassroomAutoDto {
    CourseDto course;
    Integer size;
}
