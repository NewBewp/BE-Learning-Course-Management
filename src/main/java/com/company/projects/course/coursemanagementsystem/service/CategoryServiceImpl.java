package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.CategoryDto;
import com.company.projects.course.coursemanagementsystem.mapper.CategoryMapper;
import com.company.projects.course.coursemanagementsystem.model.CategoryEntity;
import com.company.projects.course.coursemanagementsystem.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends BaseServiceImpl<String, CategoryDto, CategoryEntity> implements CategoryService {
    public CategoryServiceImpl(CategoryRepository repository, CategoryMapper mapper) {
        super(repository, mapper, "Category");
    }
}
