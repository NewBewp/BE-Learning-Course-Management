package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.ClassroomDto;
import com.company.projects.course.coursemanagementsystem.dto.CreateClassroomAutoDto;
import com.company.projects.course.coursemanagementsystem.dto.StudentDto;
import org.springframework.data.domain.Page;

public interface ClassroomService extends BaseService<String, ClassroomDto> {
    ClassroomDto addStudent(String id, StudentDto studentDto);
    ClassroomDto removeStudent(String id, StudentDto studentDto);
    Page<ClassroomDto> search(String name, int page, int size, String sort);
    Page<ClassroomDto> filter(String courseId, String studentId, int page, int size, String sort);
    void createClassroomAuto(CreateClassroomAutoDto createClassroomAutoDto);
}
