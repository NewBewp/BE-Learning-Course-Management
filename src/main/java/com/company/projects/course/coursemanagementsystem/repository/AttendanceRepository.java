package com.company.projects.course.coursemanagementsystem.repository;

import com.company.projects.course.coursemanagementsystem.model.AttendanceEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends BaseRepository<AttendanceEntity, String> {
}