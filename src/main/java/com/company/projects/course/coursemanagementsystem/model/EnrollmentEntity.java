package com.company.projects.course.coursemanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "tbl_enrollment")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
public class EnrollmentEntity extends BaseEntity {
    @Column(nullable = false)
    Boolean status;

    @Column(length = 4000)
    String note;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    @JsonBackReference
    CourseEntity course;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    @JsonBackReference
    StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "record_date", nullable = false)
    @JsonBackReference
    DateEntity date;
}
