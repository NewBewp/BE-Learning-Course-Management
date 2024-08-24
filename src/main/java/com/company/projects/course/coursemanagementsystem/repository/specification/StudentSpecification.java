package com.company.projects.course.coursemanagementsystem.repository.specification;

import com.company.projects.course.coursemanagementsystem.model.StudentEntity;
import com.company.projects.course.coursemanagementsystem.repository.specification.common.NPESpecification;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentSpecification extends NPESpecification<StudentEntity> {

}
