package com.company.projects.course.coursemanagementsystem.repository.custom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Collection;

@NoRepositoryBean
public interface CustomPhoneRepository<E, I> extends JpaRepository<E, I> {
    Collection<E> findAllByPhone(String phone);
    boolean existsByPhone(String phone);
    boolean existsByPhoneIgnoreCase(String phone);
}
