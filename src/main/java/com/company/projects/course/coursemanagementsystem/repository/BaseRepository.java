package com.company.projects.course.coursemanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T, I> extends JpaRepository<T, I> {
    @Query("SELECT e FROM #{#entityName} e WHERE e.id = ?1 AND e.deleted = false")
    Optional<T> findByIdAndDeletedFalse(I id);

    @Query("SELECT e FROM #{#entityName} e WHERE e.deleted = false")
    Collection<T> findAllByDeletedFalse();
    @Modifying
    @Transactional
    @Query("UPDATE #{#entityName} e SET e.deleted = true WHERE e.id = ?1")
    void softDeleteById(I id);
}

