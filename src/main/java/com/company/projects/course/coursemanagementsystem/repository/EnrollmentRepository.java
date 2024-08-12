package com.company.projects.course.coursemanagementsystem.repository;

import com.company.projects.course.coursemanagementsystem.model.EnrollmentEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends BaseRepository<EnrollmentEntity, String> {
}