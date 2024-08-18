package com.company.projects.course.coursemanagementsystem.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

@NoRepositoryBean
public interface CustomNameRepository<E, I> extends JpaRepository<E, I> {
    @Query("SELECT e FROM #{#entityName} e WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%')) AND e.deleted = false")
    Page<E> findAllByNameAndDeletedFalse(@Param("name") String name, Pageable pageable);
    boolean existsByName(String name);
    boolean existsByNameIgnoreCase(String name);
}
