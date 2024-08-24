package com.company.projects.course.coursemanagementsystem.repository.specification;

import com.company.projects.course.coursemanagementsystem.model.EnrollmentEntity;
import com.company.projects.course.coursemanagementsystem.model.StudentEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentSpecification {
    public static Specification<EnrollmentEntity> filter(String status, LocalDate date, String courseId, String studentId) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (status != null && !status.isEmpty()) predicates.add(cb.equal(root.get("status"), status));
            if (date != null) predicates.add(cb.equal(root.get("date"), date));
            if (courseId != null && !courseId.isEmpty()) predicates.add(cb.equal(root.get("course").get("id"), courseId));
            if (studentId != null && !studentId.isEmpty()) predicates.add(cb.equal(root.get("student").get("id"), studentId));
            predicates.add(cb.isFalse(root.get("deleted")));
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<EnrollmentEntity> filterByCriteria(String courseId, String status) {
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
