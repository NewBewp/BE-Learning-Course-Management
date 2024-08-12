package com.company.projects.course.coursemanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.Collection;

@Entity
@Table(name = "tbl_category")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
public class CategoryEntity extends BaseEntity {
    @Column(nullable = false, unique = true)
    String name;

    @OneToMany(mappedBy = "category")
    @JsonManagedReference
    Collection<CourseEntity> courses;
}
