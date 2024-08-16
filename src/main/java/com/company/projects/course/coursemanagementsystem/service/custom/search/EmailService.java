package com.company.projects.course.coursemanagementsystem.service.custom.search;

import java.util.Collection;

public interface EmailService <D>{
    Collection<D> searchAllByEmail(String email);
}
