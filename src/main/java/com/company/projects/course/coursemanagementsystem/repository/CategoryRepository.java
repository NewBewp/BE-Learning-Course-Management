package com.company.projects.course.coursemanagementsystem.repository;

import com.company.projects.course.coursemanagementsystem.model.CategoryEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends BaseRepository<CategoryEntity, String> {
}