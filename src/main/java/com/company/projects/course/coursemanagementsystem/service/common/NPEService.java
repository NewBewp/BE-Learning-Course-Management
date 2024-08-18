package com.company.projects.course.coursemanagementsystem.service.common;

import org.springframework.data.jpa.domain.Specification;

public interface NPEService<E> {
    Specification<E> search(String name, String phone, String email);
}
