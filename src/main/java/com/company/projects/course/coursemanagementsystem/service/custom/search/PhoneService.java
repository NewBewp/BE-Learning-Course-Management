package com.company.projects.course.coursemanagementsystem.service.custom.search;

import java.util.Collection;

public interface PhoneService<D> {
    Collection<D> searchAllByPhone(String phone);
}
