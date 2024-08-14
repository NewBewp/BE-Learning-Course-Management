package com.company.projects.course.coursemanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.Collection;

@Entity
@Table(name = "tbl_classroom")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
public class ClassroomEntity extends BaseEntity {
    @Column(nullable = false)
    String name;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    @JsonBackReference
    CourseEntity course;

    @ManyToMany
    @JoinTable(
        name = "tbl_classroom_student",
        joinColumns = @JoinColumn(name = "classroom_id", nullable = false),
        inverseJoinColumns = @JoinColumn(name = "student_id", nullable = false)
    )
    @JsonBackReference
    Collection<StudentEntity> students;

    @OneToMany(mappedBy = "classroom", fetch = FetchType.LAZY)
    @JsonManagedReference
    Collection<AttendanceEntity> attendances;

    @OneToMany(mappedBy = "classroom", fetch = FetchType.LAZY)
    @JsonManagedReference
    Collection<AssignmentEntity> assignments;
}
