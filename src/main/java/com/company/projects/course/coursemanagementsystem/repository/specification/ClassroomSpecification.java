package com.company.projects.course.coursemanagementsystem.repository.specification;

import com.company.projects.course.coursemanagementsystem.model.ClassroomEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ClassroomSpecification {
    public static Specification<ClassroomEntity> filterByCriteria(String courseId, String studentId) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (courseId != null && !courseId.isEmpty())
                predicates.add(criteriaBuilder.equal(root.get("course").get("id"), courseId));
            if (studentId != null && !studentId.isEmpty())
                predicates.add(criteriaBuilder.equal(root.get("student").get("id"), studentId));
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
