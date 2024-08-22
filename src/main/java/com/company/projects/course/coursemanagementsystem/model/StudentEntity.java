package com.company.projects.course.coursemanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.Collection;

@Entity
@Table(name = "tbl_student")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
public class StudentEntity extends BasePerson {
    @OneToMany(mappedBy = "student")
    @JsonManagedReference
    Collection<EnrollmentEntity> enrollments;

    @ManyToMany(mappedBy = "students")
    @JsonManagedReference
    Collection<ClassroomEntity> classrooms;

    @OneToMany(mappedBy = "student")
    @JsonManagedReference
    Collection<AttendanceEntity> attendances;
}
