package com.company.projects.course.coursemanagementsystem.repository.custom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Collection;
import java.util.Optional;

@NoRepositoryBean
public interface CustomEmailRepository<E, I> extends JpaRepository<E, I> {
    Optional<E> findByEmailAndDeletedFalse(String email);
    boolean existsByEmail(String email);
    boolean existsByEmailIgnoreCase(String email);
}
