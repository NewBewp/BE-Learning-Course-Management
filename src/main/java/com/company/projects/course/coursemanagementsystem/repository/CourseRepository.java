package com.company.projects.course.coursemanagementsystem.repository;

import com.company.projects.course.coursemanagementsystem.model.CourseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends BaseRepository<CourseEntity, String> {
}