package com.company.projects.course.coursemanagementsystem.repository.specification;

import com.company.projects.course.coursemanagementsystem.model.AttendanceEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AttendanceSpecification {
    public static Specification<AttendanceEntity> filterByCriteria(Boolean status, LocalDate date, String studentId, String classroomId) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (status != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), status));
            }
            if (date != null) {
                predicates.add(criteriaBuilder.equal(root.get("dated"), date));
            }
            if (studentId != null) {
                predicates.add(criteriaBuilder.equal(root.get("student").get("id"), studentId));
            }
            if (classroomId != null) {
                predicates.add(criteriaBuilder.equal(root.get("classroom").get("id"), classroomId));
            }
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
