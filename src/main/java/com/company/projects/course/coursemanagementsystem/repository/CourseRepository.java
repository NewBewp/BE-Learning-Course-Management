package com.company.projects.course.coursemanagementsystem.repository;

import com.company.projects.course.coursemanagementsystem.model.CourseEntity;
import com.company.projects.course.coursemanagementsystem.repository.custom.CustomNameRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface CourseRepository extends BaseRepository<CourseEntity, String>,
        CustomNameRepository<CourseEntity, String> {
    @Override
    @Query("SELECT a FROM CourseEntity a " +
            "JOIN a.category u " +
            "JOIN a.company c " +
            "WHERE a.deleted = false AND u.deleted = false AND c.deleted = false")
    Page<CourseEntity> findAllByDeletedFalse(Pageable pageable);

    @Override
    @Query("SELECT a FROM CourseEntity a " +
            "JOIN a.category u " +
            "JOIN a.company c " +
            "WHERE a.id = ?1 AND a.deleted = false AND u.deleted = false AND c.deleted = false")
    Optional<CourseEntity> findByIdAndDeletedFalse(String id);
}