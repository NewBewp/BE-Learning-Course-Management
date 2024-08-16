package com.company.projects.course.coursemanagementsystem.repository;

import com.company.projects.course.coursemanagementsystem.model.AccountEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface AccountRepository extends BaseRepository<AccountEntity, String> {
    boolean existsByUsername(String username);
    boolean existsByUsernameIgnoreCase(String username);

    @Override
    @Query("SELECT a FROM AccountEntity a " +
            "JOIN a.user u " +
            "JOIN a.role c " +
            "WHERE a.deleted = false AND u.deleted = false AND c.deleted = false")
    Page<AccountEntity> findAllByDeletedFalse(Pageable pageable);

    @Override
    @Query("SELECT a FROM AccountEntity a " +
            "JOIN a.user u " +
            "JOIN a.role c " +
            "WHERE a.id = ?1 AND a.deleted = false AND u.deleted = false AND c.deleted = false")
    Optional<AccountEntity> findByIdAndDeletedFalse(String id);
}

