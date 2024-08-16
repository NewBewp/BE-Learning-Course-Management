package com.company.projects.course.coursemanagementsystem.service.custom.search;

import org.springframework.data.domain.Page;

public interface NameService <D>{
    Page<D> searchAllByName(String name, int page, int size);
}

