package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.ClassroomDto;
import com.company.projects.course.coursemanagementsystem.dto.StudentDto;

public interface ClassroomService extends BaseService<String, ClassroomDto> {
    ClassroomDto addStudent(String id, StudentDto studentDto);
    ClassroomDto removeStudent(String id, StudentDto studentDto);
}
