package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.CategoryDto;
import org.springframework.data.domain.Page;

public interface CategoryService extends BaseService<String, CategoryDto> {
    Page<CategoryDto> search(String name, int page, int size, String sort);
}
