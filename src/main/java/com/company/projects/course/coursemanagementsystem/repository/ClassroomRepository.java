package com.company.projects.course.coursemanagementsystem.repository;

import com.company.projects.course.coursemanagementsystem.model.ClassroomEntity;
import com.company.projects.course.coursemanagementsystem.repository.custom.CustomNameRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomRepository extends BaseRepository<ClassroomEntity, String>,
        CustomNameRepository<ClassroomEntity, String> {
}