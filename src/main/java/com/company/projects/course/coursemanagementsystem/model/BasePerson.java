package com.company.projects.course.coursemanagementsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
public abstract class BasePerson extends BaseEntity {
    @Column(nullable = false)
    String name;

    @Column(nullable = false, length = 10)
    String gender;

    @Column(nullable = false)
    LocalDate birthday;

    @Column(nullable = false, length = 20)
    String phone;

    @Column(nullable = false)
    String email;

    @Column(nullable = false, length = 4000)
    String address;
}
