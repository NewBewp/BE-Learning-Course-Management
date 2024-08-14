package com.company.projects.course.coursemanagementsystem.repository;

import com.company.projects.course.coursemanagementsystem.model.CompanyEntity;
import com.company.projects.course.coursemanagementsystem.repository.custom.CustomEmailRepository;
import com.company.projects.course.coursemanagementsystem.repository.custom.CustomNameRepository;
import com.company.projects.course.coursemanagementsystem.repository.custom.CustomPhoneRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends BaseRepository<CompanyEntity, String>,
        CustomNameRepository<CompanyEntity, String>,
        CustomPhoneRepository<CompanyEntity, String>,
        CustomEmailRepository<CompanyEntity, String> {
}