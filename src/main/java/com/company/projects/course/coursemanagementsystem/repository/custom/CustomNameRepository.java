package com.company.projects.course.coursemanagementsystem.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Collection;

@NoRepositoryBean
public interface CustomNameRepository<E, I> extends JpaRepository<E, I> {
    Page<E> findAllByNameAndDeletedFalse(String name, Pageable pageable);
    boolean existsByName(String name);
    boolean existsByNameIgnoreCase(String name);
}
