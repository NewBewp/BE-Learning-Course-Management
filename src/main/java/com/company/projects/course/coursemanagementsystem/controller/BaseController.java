package com.company.projects.course.coursemanagementsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

public interface BaseController<I, D> {
    ResponseEntity<D> getById(I id);
    ResponseEntity<Collection<D>> getAll();
    ResponseEntity<D> create(D dto);
    ResponseEntity<Void> delete(I id);
}
