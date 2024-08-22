package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.CourseDto;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface CourseService extends BaseService<String, CourseDto> {
    Page<CourseDto> search(String name, int page, int size, String sort);
    Page<CourseDto> filter(LocalDate startDate, LocalDate endDate, String categoryId, String companyId, int page, int size, String sort);

}
