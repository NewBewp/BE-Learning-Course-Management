package com.company.projects.course.coursemanagementsystem.repository;

import com.company.projects.course.coursemanagementsystem.model.CompanyEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends BaseRepository<CompanyEntity, String> {
}