package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.exception.custom.EmptyResultDataAccessException;
import com.company.projects.course.coursemanagementsystem.exception.custom.EntityNotFoundException;
import com.company.projects.course.coursemanagementsystem.mapper.EntityMapper;
import com.company.projects.course.coursemanagementsystem.repository.BaseRepository;
import com.company.projects.course.coursemanagementsystem.util.JPAUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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
    public Page<D> findAll(int page, int size, String sort) {
        Sort sortBy = JPAUtil.getSortRequestParam(sort);
        Pageable pageable = PageRequest.of(page, size, sortBy);
        Page<E> results = repository.findAllByDeletedFalse(pageable);
        if (results.isEmpty()) throw new EmptyResultDataAccessException(entityName + " is empty");
        return results.map(mapper::toDto);
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

    @Override
    public D update(I id, D dto) {
        E existingEntity = repository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException(entityName + " not found with id " + id));
        E updatedEntity = mapper.updateEntity(dto, existingEntity);
        E savedEntity = repository.save(updatedEntity);
        return mapper.toDto(savedEntity);
    }
}
