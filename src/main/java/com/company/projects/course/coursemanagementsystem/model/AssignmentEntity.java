package com.company.projects.course.coursemanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "tbl_assignment")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
public class AssignmentEntity extends BaseEntity {
    @Column(length = 4000)
    String note;

    @ManyToOne
    @JoinColumn(name = "classroom_id", nullable = false)
    @JsonBackReference
    ClassroomEntity classroom;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    UserEntity user;
}
