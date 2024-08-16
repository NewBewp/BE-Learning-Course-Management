package com.company.projects.course.coursemanagementsystem.repository;

import com.company.projects.course.coursemanagementsystem.model.EnrollmentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends BaseRepository<EnrollmentEntity, String> {
    @Override
    @Query("SELECT a FROM EnrollmentEntity a " +
            "JOIN a.student u " +
            "JOIN a.course c " +
            "WHERE a.deleted = false AND u.deleted = false AND c.deleted = false")
    Page<EnrollmentEntity> findAllByDeletedFalse(Pageable pageable);

    @Override
    @Query("SELECT a FROM EnrollmentEntity a " +
            "JOIN a.student u " +
            "JOIN a.course c " +
            "WHERE a.id = ?1 AND a.deleted = false AND u.deleted = false AND c.deleted = false")
    Optional<EnrollmentEntity> findByIdAndDeletedFalse(String id);
}