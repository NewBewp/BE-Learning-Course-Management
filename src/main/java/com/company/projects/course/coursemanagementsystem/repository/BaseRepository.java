package com.company.projects.course.coursemanagementsystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T, I> extends JpaRepository<T, I>, PagingAndSortingRepository<T, I> {
    @Query("SELECT e FROM #{#entityName} e WHERE e.id = ?1 AND e.deleted = false")
    Optional<T> findByIdAndDeletedFalse(I id);

    @Query("SELECT e FROM #{#entityName} e WHERE e.deleted = false")
    Page<T> findAllByDeletedFalse(Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE #{#entityName} e SET e.deleted = true WHERE e.id = ?1")
    void softDeleteById(I id);


}

