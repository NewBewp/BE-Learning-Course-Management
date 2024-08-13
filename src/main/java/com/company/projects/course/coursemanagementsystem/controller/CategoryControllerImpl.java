package com.company.projects.course.coursemanagementsystem.controller;

import com.company.projects.course.coursemanagementsystem.dto.CategoryDto;
import com.company.projects.course.coursemanagementsystem.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryControllerImpl extends BaseControllerImpl<String, CategoryDto, CategoryService> implements CategoryController{
    @Autowired
    public CategoryControllerImpl(CategoryService service) {
        super(service);
    }
}
