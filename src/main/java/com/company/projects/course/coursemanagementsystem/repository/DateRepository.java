package com.company.projects.course.coursemanagementsystem.repository;

import com.company.projects.course.coursemanagementsystem.model.DateEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface DateRepository extends BaseRepository<DateEntity, LocalDate> {
}