package com.company.projects.course.coursemanagementsystem.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface BaseController<I, D> {
    ResponseEntity<D> getById(I id);
    ResponseEntity<Page<D>> getAll(int page, int size);
    ResponseEntity<D> create(D dto);
    ResponseEntity<Void> delete(I id);
    ResponseEntity<D> update(I id, D dto);
}
