package com.company.projects.course.coursemanagementsystem.controller;

import com.company.projects.course.coursemanagementsystem.dto.ClassroomDto;
import com.company.projects.course.coursemanagementsystem.dto.StudentDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface ClassroomController extends BaseController<String, ClassroomDto> {
    ResponseEntity<ClassroomDto> addStudent(String id, StudentDto studentDto);
    ResponseEntity<ClassroomDto> removeStudent(String id, StudentDto studentDto);
    ResponseEntity<Page<ClassroomDto>> search(String name, int page, int size, String sort);
    ResponseEntity<Page<ClassroomDto>> filter(String courseId, String studentId, int page, int size, String sort);
}

