package com.company.projects.course.coursemanagementsystem.service.custom.search;

import java.util.Collection;

public interface NameService <D>{
    Collection<D> searchAllByName(String name);
}
