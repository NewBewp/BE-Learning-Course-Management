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
    public Specification<StudentEntity> filterByCriteria(String courseId, String status) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (courseId!= null &&!courseId.isEmpty()) {
                predicates.add(cb.equal(root.get("course").get("id"), courseId));
            }
            if (status!= null &&!status.isEmpty()) {
                predicates.add(cb.equal(root.get("status"), status));
            }
            predicates.add(cb.isFalse(root.get("deleted")));
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
