package com.company.projects.course.coursemanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.Collection;

@Entity
@Table(name = "tbl_user")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
public class UserEntity extends BasePerson {
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    Collection<AssignmentEntity> assignments;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    Collection<AccountEntity> accounts;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    @JsonBackReference
    CompanyEntity company;
}
