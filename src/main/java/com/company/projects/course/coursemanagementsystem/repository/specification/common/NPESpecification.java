package com.company.projects.course.coursemanagementsystem.repository.specification.common;

import com.company.projects.course.coursemanagementsystem.util.CheckFieldUtil;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class NPESpecification<E> {
    public Specification<E> searchByCriteria(String name, String phone, String email) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (phone != null && !phone.isEmpty() && CheckFieldUtil.isValidPhone(phone)) {
                predicates.add(cb.like(cb.lower(root.get("phone")), "%" + phone.toLowerCase() + "%"));
            } else if (email != null && !email.isEmpty() && CheckFieldUtil.isValidEmail(email)) {
                predicates.add(cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%"));
            } else {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }

            predicates.add(cb.isFalse(root.get("deleted")));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    public Specification<E> filterByCriteria(String gender, LocalDate birthday) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (gender != null && !gender.isEmpty()) {
                predicates.add(cb.equal(root.get("gender"), gender));
            }
            if (birthday != null) {
                predicates.add(cb.equal(root.get("birthday"), birthday));
            }
            predicates.add(cb.isFalse(root.get("deleted")));
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
