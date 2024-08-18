package com.company.projects.course.coursemanagementsystem.repository.specification;

import com.company.projects.course.coursemanagementsystem.model.AssignmentEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class AssignmentSpecification {
    public static Specification<AssignmentEntity> filterByCriteria(String userId, String classroomId) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (userId != null && !userId.isEmpty()) predicates.add(criteriaBuilder.equal(root.get("user").get("id"), userId));
            if (classroomId != null && !classroomId.isEmpty()) predicates.add(criteriaBuilder.equal(root.get("classroom").get("id"), classroomId));
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
