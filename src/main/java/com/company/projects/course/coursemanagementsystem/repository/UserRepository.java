package com.company.projects.course.coursemanagementsystem.repository;

import com.company.projects.course.coursemanagementsystem.model.UserEntity;
import com.company.projects.course.coursemanagementsystem.repository.custom.CustomEmailRepository;
import com.company.projects.course.coursemanagementsystem.repository.custom.CustomNameRepository;
import com.company.projects.course.coursemanagementsystem.repository.custom.CustomPhoneRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<UserEntity, String>,
        CustomNameRepository<UserEntity, String>,
        CustomPhoneRepository<UserEntity, String>,
        CustomEmailRepository<UserEntity, String> {
}