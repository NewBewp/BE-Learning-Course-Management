package com.company.projects.course.coursemanagementsystem.controller;

import com.company.projects.course.coursemanagementsystem.dto.CourseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public interface CourseController extends BaseController<String, CourseDto> {
    ResponseEntity<Page<CourseDto>> search(String name, int page, int size, String sort);
    ResponseEntity<Page<CourseDto>> filter(LocalDate startDate, LocalDate endDate, String categoryId, String companyId, int page, int size, String sort);
    ResponseEntity<CourseDto> createCourse(String courseJson, MultipartFile image);
}
