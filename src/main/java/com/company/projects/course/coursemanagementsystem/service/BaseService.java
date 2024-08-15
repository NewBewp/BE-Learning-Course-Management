package com.company.projects.course.coursemanagementsystem.service;

import java.util.Collection;

public interface BaseService<I, D> {
    D findById(I id);
    Collection<D> findAll();
    D save(D dto);
    void deleteById(I id);
    D update(I id, D dto);
    Collection<D> searchAllByName(String name);
    Collection<D> searchAllByPhone(String phone);
    Collection<D> searchByEmail(String email);
}
