package com.company.projects.course.coursemanagementsystem.repository;

import com.company.projects.course.coursemanagementsystem.model.RoleEntity;
import com.company.projects.course.coursemanagementsystem.repository.custom.CustomNameRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface RoleRepository extends BaseRepository<RoleEntity, String>,
        CustomNameRepository<RoleEntity, String> {
    @Override
    @Query("SELECT a FROM RoleEntity a " +
            "JOIN a.permissions c " +
            "WHERE a.deleted = false AND c.deleted = false")
    Page<RoleEntity> findAllByDeletedFalse(Pageable pageable);

    @Override
    @Query("SELECT a FROM RoleEntity a " +
            "JOIN a.permissions c " +
            "WHERE a.id = ?1 AND a.deleted = false AND c.deleted = false")
    Optional<RoleEntity> findByIdAndDeletedFalse(String id);
}