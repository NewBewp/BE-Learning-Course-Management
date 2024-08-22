package com.company.projects.course.coursemanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.Collection;

@Entity
@Table(name = "tbl_company")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
public class CompanyEntity extends BaseEntity{
    @Column(nullable = false)
    String name;

    @Column(nullable = false, unique = true, length = 20)
    String phone;

    @Column(nullable = false, unique = true)
    String email;

    @Column(nullable = false, length = 4000)
    String address;

    @OneToMany(mappedBy = "company")
    @JsonManagedReference
    Collection<CourseEntity> courses;

    @OneToMany(mappedBy = "company")
    @JsonManagedReference
    Collection<UserEntity> users;
}
