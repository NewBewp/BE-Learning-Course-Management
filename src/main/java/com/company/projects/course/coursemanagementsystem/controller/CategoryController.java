package com.company.projects.course.coursemanagementsystem.controller;

import com.company.projects.course.coursemanagementsystem.dto.CategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface CategoryController extends BaseController<String, CategoryDto>{
    ResponseEntity<Page<CategoryDto>> search(String name, int page, int size, String sort);
}
