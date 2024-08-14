package com.company.projects.course.coursemanagementsystem.repository;

import com.company.projects.course.coursemanagementsystem.model.StudentEntity;
import com.company.projects.course.coursemanagementsystem.repository.custom.CustomEmailRepository;
import com.company.projects.course.coursemanagementsystem.repository.custom.CustomNameRepository;
import com.company.projects.course.coursemanagementsystem.repository.custom.CustomPhoneRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends BaseRepository<StudentEntity, String>,
        CustomNameRepository<StudentEntity, String>,
        CustomPhoneRepository<StudentEntity, String>,
        CustomEmailRepository<StudentEntity, String> {
}