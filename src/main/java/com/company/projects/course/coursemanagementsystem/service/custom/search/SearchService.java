package com.company.projects.course.coursemanagementsystem.service.custom.search;

import java.util.Collection;

public interface SearchService <D> {
    Collection<D> searchAllByName(String name);
    Collection<D> searchAllByPhone(String phone);
    Collection<D> searchByEmail(String email);
}
