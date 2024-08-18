package com.company.projects.course.coursemanagementsystem.repository;

import com.company.projects.course.coursemanagementsystem.model.AttendanceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttendanceRepository extends BaseRepository<AttendanceEntity, String> {
    @Override
    @Query("SELECT a FROM AttendanceEntity a " +
            "JOIN a.student u " +
            "JOIN a.classroom c " +
            "WHERE a.deleted = false AND u.deleted = false AND c.deleted = false")
    Page<AttendanceEntity> findAllByDeletedFalse(Pageable pageable);

    @Override
    @Query("SELECT a FROM AttendanceEntity a " +
            "JOIN a.student u " +
            "JOIN a.classroom c " +
            "WHERE a.id = ?1 AND a.deleted = false AND u.deleted = false AND c.deleted = false")
    Optional<AttendanceEntity> findByIdAndDeletedFalse(String id);
}

