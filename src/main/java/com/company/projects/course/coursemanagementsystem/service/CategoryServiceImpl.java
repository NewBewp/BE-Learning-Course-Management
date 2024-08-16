package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.CategoryDto;
import com.company.projects.course.coursemanagementsystem.exception.custom.EmptyResultDataAccessException;
import com.company.projects.course.coursemanagementsystem.mapper.CategoryMapper;
import com.company.projects.course.coursemanagementsystem.model.CategoryEntity;
import com.company.projects.course.coursemanagementsystem.repository.CategoryRepository;
import com.company.projects.course.coursemanagementsystem.service.custom.search.NameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CategoryServiceImpl extends BaseServiceImpl<String, CategoryDto, CategoryEntity> implements CategoryService, NameService<CategoryDto> {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository, CategoryMapper mapper) {
        super(repository, mapper, "Category");
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Collection<CategoryDto> searchAllByName(String name) {
        Collection<CategoryEntity> results = repository.findAllByNameAndDeletedFalse(name);
        if (results.isEmpty()) throw new EmptyResultDataAccessException("Category" + " not found with name = " + name);
        return results.stream().map(mapper::toDto).toList();
    }
}
