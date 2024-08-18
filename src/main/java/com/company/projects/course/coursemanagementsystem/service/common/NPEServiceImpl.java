package com.company.projects.course.coursemanagementsystem.service.common;

import com.company.projects.course.coursemanagementsystem.repository.specification.common.NPESpecification;
import org.springframework.data.jpa.domain.Specification;

public class NPEServiceImpl<E> implements NPEService<E> {
    private final NPESpecification<E> npeSpecification;
    public NPEServiceImpl(NPESpecification<E> npeSpecification) {
        this.npeSpecification = npeSpecification;
    }
    @Override
    public Specification<E> search(String name, String phone, String email) {
        return npeSpecification.searchByCriteria(name, phone, email);
    }
}
