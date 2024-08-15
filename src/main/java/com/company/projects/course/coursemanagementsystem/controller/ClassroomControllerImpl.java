package com.company.projects.course.coursemanagementsystem.controller;

import com.company.projects.course.coursemanagementsystem.dto.ClassroomDto;
import com.company.projects.course.coursemanagementsystem.dto.StudentDto;
import com.company.projects.course.coursemanagementsystem.repository.ClassroomRepository;
import com.company.projects.course.coursemanagementsystem.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/classrooms")
public class ClassroomControllerImpl extends BaseControllerImpl<String, ClassroomDto, ClassroomService> implements ClassroomController {
    ClassroomService classroomService;
    @Autowired
    public ClassroomControllerImpl(ClassroomService service, ClassroomService classroomService) {
        super(service);
        this.classroomService = classroomService;
    }

    @Override
    @PutMapping("/{id}/add_student")
    public ResponseEntity<ClassroomDto> addStudent(@PathVariable String id, StudentDto studentDto) {
        ClassroomDto classroomDto = classroomService.addStudent(id, studentDto);
        return ResponseEntity.ok(classroomDto);
    }

    @Override
    @PutMapping("/{id}/remove_student")
    public ResponseEntity<ClassroomDto> removeStudent(@PathVariable String id, StudentDto studentDto) {
        ClassroomDto classroomDto = classroomService.removeStudent(id, studentDto);
        return ResponseEntity.ok(classroomDto);
    }
}
