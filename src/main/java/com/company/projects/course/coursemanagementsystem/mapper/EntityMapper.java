package com.company.projects.course.coursemanagementsystem.mapper;

public interface EntityMapper<E, D>{
    E toEntity(D dto);
    D toDto(E entity);
    E updateEntity(D dto, E entity);
}
