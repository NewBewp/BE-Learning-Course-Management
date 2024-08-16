package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.service.custom.search.EmailService;
import com.company.projects.course.coursemanagementsystem.service.custom.search.NameService;
import com.company.projects.course.coursemanagementsystem.service.custom.search.PhoneService;

import java.util.Collection;

public interface BaseService<I, D> extends EmailService<D>, NameService<D>, PhoneService<D> {
    D findById(I id);
    Collection<D> findAll();
    D save(D dto);
    void deleteById(I id);
    D update(I id, D dto);
}
