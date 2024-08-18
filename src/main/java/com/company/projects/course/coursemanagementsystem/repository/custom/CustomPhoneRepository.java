package com.company.projects.course.coursemanagementsystem.repository.custom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface CustomPhoneRepository<E, I> extends JpaRepository<E, I> {
    Optional<E> findByPhoneAndDeletedFalse(String phone);
    boolean existsByPhone(String phone);
    boolean existsByPhoneIgnoreCase(String phone);
}

