package com.company.projects.course.coursemanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
    LocalDate recordDate;

    @OneToMany(mappedBy = "date")
    @JsonManagedReference
    Collection<EnrollmentEntity> enrollments;

    @OneToMany(mappedBy = "date")
    @JsonManagedReference
    Collection<AttendanceEntity> attendances;

    @Column(nullable = false, updatable = false)
    LocalDate createdAt;

    @Column(nullable = false)
    LocalDate updatedAt;

    @Column(nullable = false)
    Boolean deleted;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
        this.deleted = false;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDate.now();
    }
}
