package com.company.projects.course.coursemanagementsystem.controller;

import com.company.projects.course.coursemanagementsystem.dto.StudentDto;
import com.company.projects.course.coursemanagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentControllerImpl extends BaseControllerImpl<String, StudentDto, StudentService> implements StudentController {
    @Autowired
    public StudentControllerImpl(StudentService service) {
        super(service);
    }
}
