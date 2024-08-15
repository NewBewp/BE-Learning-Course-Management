package com.company.projects.course.coursemanagementsystem.controller.custom.search;

import org.springframework.http.ResponseEntity;

import java.util.Collection;

public interface SearchController<D> {
    ResponseEntity<Collection<D>> searchAllByName(String name);
    ResponseEntity<Collection<D>> searchAllByPhone(String phone);
    ResponseEntity<Collection<D>> searchAllByEmail(String email);
}
