package com.company.projects.course.coursemanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "tbl_attendance")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
public class AttendanceEntity extends BaseEntity {
    @Column(nullable = false)
    Boolean status;

    @Column(length = 4000)
    String reason;

    @ManyToOne
    @JoinColumn(name = "classroom_id", nullable = false)
    @JsonBackReference
    ClassroomEntity classroom;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    @JsonBackReference
    StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "date", nullable = false)
    @JsonBackReference
    DateEntity date;

}
