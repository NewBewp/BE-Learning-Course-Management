package com.company.projects.course.coursemanagementsystem.controller;

import com.company.projects.course.coursemanagementsystem.service.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

public abstract class BaseControllerImpl <I, D, S extends BaseService<I, D>> implements BaseController <I, D>{

    protected S service;

    protected BaseControllerImpl(S service) {
        this.service = service;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<D> getById(@PathVariable I id) {
        D dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @Override
    @GetMapping
    public ResponseEntity<Collection<D>> getAll() {
        Collection<D> dtos = service.findAll();
        return ResponseEntity.ok(dtos);
    }

    @Override
    @PostMapping
    public ResponseEntity<D> create(@RequestBody D dto) {
        D createdDto = service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable I id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
