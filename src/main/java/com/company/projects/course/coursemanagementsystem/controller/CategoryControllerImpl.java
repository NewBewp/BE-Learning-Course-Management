package com.company.projects.course.coursemanagementsystem.controller;

import com.company.projects.course.coursemanagementsystem.dto.CategoryDto;
import com.company.projects.course.coursemanagementsystem.security.CustomUserDetails;
import com.company.projects.course.coursemanagementsystem.service.CategoryService;
import com.company.projects.course.coursemanagementsystem.service.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@PreAuthorize("hasRole('admin')")
public class CategoryControllerImpl extends BaseControllerImpl<String, CategoryDto, CategoryService> implements CategoryController{
    private final CategoryService categoryService;
    private final CurrentUserService currentUserService;
    @Autowired
    public CategoryControllerImpl(CategoryService service, CurrentUserService currentUserService) {
        super(service);
        this.categoryService = service;
        this.currentUserService = currentUserService;
    }

    @Override
    @GetMapping("/search")
    public ResponseEntity<Page<CategoryDto>> search(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "updatedAt:desc") String sort) {
        System.out.println("Role" + currentUserService.getCurrentUserRole());
        CustomUserDetails userDetails = currentUserService.getCurrentUserDetails();
        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.getCompanyId());
        Page<CategoryDto> results = categoryService.search(name, page, size, sort);
        return ResponseEntity.ok(results);
    }
}
