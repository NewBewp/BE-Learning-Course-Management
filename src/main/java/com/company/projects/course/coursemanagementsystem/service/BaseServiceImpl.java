package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.exception.custom.EmptyResultDataAccessException;
import com.company.projects.course.coursemanagementsystem.exception.custom.EntityNotFoundException;
import com.company.projects.course.coursemanagementsystem.mapper.EntityMapper;
import com.company.projects.course.coursemanagementsystem.repository.BaseRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Collection;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class BaseServiceImpl<I, D, E> implements BaseService<I, D> {

    BaseRepository<E, I> repository;
    EntityMapper<E, D> mapper;
    String entityName;

    @Override
    public D findById(I id) {
        E result = repository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException(entityName + " not found with id = " + id));
        return mapper.toDto(result);
    }

    @Override
    public Collection<D> findAll() {
        Collection<E> result = repository.findAllByDeletedFalse();
        if (result.isEmpty()) throw new EmptyResultDataAccessException(entityName + " is empty");
        return result.stream().map(mapper::toDto).toList();
    }

    @Override
    public D save(D dto) {
        E savedEntity = repository.save(mapper.toEntity(dto));
        return mapper.toDto(savedEntity);
    }

    @Override
    public void deleteById(I id) {
        repository.softDeleteById(id);
    }
}
