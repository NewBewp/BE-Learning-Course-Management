package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.exception.custom.EmptyResultDataAccessException;
import com.company.projects.course.coursemanagementsystem.exception.custom.EntityNotFoundException;
import com.company.projects.course.coursemanagementsystem.mapper.EntityMapper;
import com.company.projects.course.coursemanagementsystem.repository.BaseRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import java.util.Collection;
import java.util.List;

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
    public Page<D> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<E> result = repository.findAllByDeletedFalse(pageable);
        if (result.isEmpty()) throw new EmptyResultDataAccessException(entityName + " is empty");
        return result.map(mapper::toDto);
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

    @Override
    public Collection<D> searchAllByEmail(String email) {
        return List.of();
    }

    @Override
    public Page<D> searchAllByName(String name, int page, int size) {
        return null;
    }

    @Override
    public Collection<D> searchAllByPhone(String phone) {
        return List.of();
    }
}
