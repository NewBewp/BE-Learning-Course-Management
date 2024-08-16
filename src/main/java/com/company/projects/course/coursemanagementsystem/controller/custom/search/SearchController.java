package com.company.projects.course.coursemanagementsystem.controller.custom.search;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

public interface SearchController<D> {
    ResponseEntity<Page<D>> searchAllByName(String name, int page, int size);
    ResponseEntity<Collection<D>> searchAllByPhone(String phone);
    ResponseEntity<Collection<D>> searchAllByEmail(String email);
}
