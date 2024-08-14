package com.company.projects.course.coursemanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.Collection;

@Entity
@Table(name = "tbl_role")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
public class RoleEntity extends BaseEntity {
    @Column(nullable = false, unique = true)
    String name;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    @JsonManagedReference
    Collection<AccountEntity> accounts;

    @ManyToMany
    @JoinTable(
        name = "tbl_role_permission",
        joinColumns = @JoinColumn(name = "role_id", nullable = false),
        inverseJoinColumns = @JoinColumn(name = "permission_id", nullable = false)
    )
    @JsonBackReference
    Collection<PermissionEntity> permissions;
}
