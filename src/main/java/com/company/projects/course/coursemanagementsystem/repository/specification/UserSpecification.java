package com.company.projects.course.coursemanagementsystem.repository.specification;

import com.company.projects.course.coursemanagementsystem.model.UserEntity;
import com.company.projects.course.coursemanagementsystem.repository.specification.common.NPESpecification;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserSpecification extends NPESpecification<UserEntity> {
    public Specification<UserEntity> filterByCriteria(String gender, LocalDate birthday, String companyId) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (gender != null && !gender.isEmpty()) {
                predicates.add(cb.equal(root.get("gender"), gender));
            }
            if (birthday != null) {
                predicates.add(cb.equal(root.get("birthday"), birthday));
            }
            if (companyId != null && !companyId.isEmpty()) {
                predicates.add(cb.equal(root.get("company").get("id"), companyId));
            }
            predicates.add(cb.isFalse(root.get("deleted")));
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
