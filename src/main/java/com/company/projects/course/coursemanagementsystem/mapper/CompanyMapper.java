package com.company.projects.course.coursemanagementsystem.mapper;

import com.company.projects.course.coursemanagementsystem.dto.CompanyDto;
import com.company.projects.course.coursemanagementsystem.model.CompanyEntity;
import com.company.projects.course.coursemanagementsystem.util.MapperUtil;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CompanyMapper implements EntityMapper<CompanyEntity, CompanyDto> {
    @Nonnull MapperUtil mapperUtil;
    @Nonnull CourseMapper courseMapper;

    @Nullable
    public CompanyEntity toEntity(@Nullable CompanyDto companyDto) {
        if (companyDto == null) return null;
        return CompanyEntity.builder()
                .id(companyDto.getId())
                .name(companyDto.getName())
                .phone(companyDto.getPhone())
                .email(companyDto.getEmail())
                .address(companyDto.getAddress())
                .courses(mapperUtil.mapCollection(companyDto.getCourses(), courseMapper::toEntity))
                .build();
    }

    @Nullable
    public CompanyDto toDto(@Nullable CompanyEntity companyEntity) {
        if (companyEntity == null) return null;
        return CompanyDto.builder()
                .id(companyEntity.getId())
                .name(companyEntity.getName())
                .phone(companyEntity.getPhone())
                .email(companyEntity.getEmail())
                .address(companyEntity.getAddress())
                .courses(mapperUtil.mapCollection(companyEntity.getCourses(), courseMapper::toDto))
                .build();
    }
}
