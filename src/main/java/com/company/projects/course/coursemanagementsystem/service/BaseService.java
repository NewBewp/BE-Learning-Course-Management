package com.company.projects.course.coursemanagementsystem.service;

import org.springframework.data.domain.Page;

public interface BaseService<I, D> {
    D findById(I id);
    Page<D> findAll(int page, int size, String sort);
    D save(D dto);
    void deleteById(I id);
    D update(I id, D dto);
}
