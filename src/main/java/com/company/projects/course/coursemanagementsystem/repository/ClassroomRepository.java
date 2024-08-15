package com.company.projects.course.coursemanagementsystem.repository;

import com.company.projects.course.coursemanagementsystem.model.ClassroomEntity;
import com.company.projects.course.coursemanagementsystem.repository.custom.CustomNameRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ClassroomRepository extends BaseRepository<ClassroomEntity, String>,
        CustomNameRepository<ClassroomEntity, String> {
    @Override
    @Query("SELECT a FROM ClassroomEntity a " +
            "JOIN a.students u " +
            "JOIN a.course c " +
            "WHERE a.deleted = false AND u.deleted = false AND c.deleted = false")
    Collection<ClassroomEntity> findAllByDeletedFalse();

    @Override
    @Query("SELECT a FROM ClassroomEntity a " +
            "JOIN a.students u " +
            "JOIN a.course c " +
            "WHERE a.id = ?1 AND a.deleted = false AND u.deleted = false AND c.deleted = false")
    Optional<ClassroomEntity> findByIdAndDeletedFalse(String id);
}