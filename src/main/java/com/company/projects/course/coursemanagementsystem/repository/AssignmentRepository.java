package com.company.projects.course.coursemanagementsystem.repository;

import com.company.projects.course.coursemanagementsystem.model.AssignmentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssignmentRepository extends BaseRepository<AssignmentEntity, String> {
    @Override
    @Query("SELECT a FROM AssignmentEntity a " +
            "JOIN a.user u " +
            "JOIN a.classroom c " +
            "WHERE a.deleted = false AND u.deleted = false AND c.deleted = false")
    Page<AssignmentEntity> findAllByDeletedFalse(Pageable pageable);

    @Override
    @Query("SELECT a FROM AssignmentEntity a " +
            "JOIN a.user u " +
            "JOIN a.classroom c " +
            "WHERE a.id = ?1 AND a.deleted = false AND u.deleted = false AND c.deleted = false")
    Optional<AssignmentEntity> findByIdAndDeletedFalse(String id);
}