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
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    @JsonManagedReference
    Collection<EnrollmentEntity> enrollments;

    @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY)
    @JsonManagedReference
    Collection<ClassroomEntity> classrooms;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    @JsonManagedReference
    Collection<AttendanceEntity> attendances;
}
