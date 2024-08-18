package com.company.projects.course.coursemanagementsystem.controller;

import com.company.projects.course.coursemanagementsystem.dto.CategoryDto;
import com.company.projects.course.coursemanagementsystem.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryControllerImpl extends BaseControllerImpl<String, CategoryDto, CategoryService> implements CategoryController{
    private final CategoryService categoryService;
    @Autowired
    public CategoryControllerImpl(CategoryService service) {
        super(service);
        this.categoryService = service;
    }

    @Override
    @GetMapping("/search")
    public ResponseEntity<Page<CategoryDto>> search(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "updated:desc") String sort) {
        Page<CategoryDto> results = categoryService.search(name, page, size, sort);
        return ResponseEntity.ok(results);
    }
}
