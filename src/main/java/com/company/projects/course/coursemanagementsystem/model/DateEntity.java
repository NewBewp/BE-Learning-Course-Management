package com.company.projects.course.coursemanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "tbl_date")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DateEntity {
    @Id
    @Column(nullable = false)
    LocalDate date;

    @OneToMany(mappedBy = "date")
    @JsonManagedReference
    Collection<EnrollmentEntity> enrollments;

    @OneToMany(mappedBy = "date")
    @JsonManagedReference
    Collection<AttendanceEntity> attendances;
}
