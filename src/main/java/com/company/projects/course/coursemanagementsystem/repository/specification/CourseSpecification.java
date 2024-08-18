package com.company.projects.course.coursemanagementsystem.repository.specification;

import com.company.projects.course.coursemanagementsystem.model.CourseEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CourseSpecification {
    public static Specification<CourseEntity> filterByCriteria(LocalDate startDate, LocalDate endDate, String categoryId, String companyId) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (startDate != null) predicates.add(cb.equal(root.get("startDate"), startDate));
            if (endDate != null) predicates.add(cb.equal(root.get("endDate"), endDate));
            if (categoryId != null &&!categoryId.isEmpty()) predicates.add(cb.equal(root.get("category").get("id"), categoryId));
            if (companyId != null &&!companyId.isEmpty()) predicates.add(cb.equal(root.get("company").get("id"), companyId));
            predicates.add(cb.isFalse(root.get("deleted")));
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
