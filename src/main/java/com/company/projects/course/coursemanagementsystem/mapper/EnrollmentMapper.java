package com.company.projects.course.coursemanagementsystem.mapper;

import com.company.projects.course.coursemanagementsystem.dto.EnrollmentDto;
import com.company.projects.course.coursemanagementsystem.model.EnrollmentEntity;
import com.company.projects.course.coursemanagementsystem.util.MapperUtil;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class EnrollmentMapper implements EntityMapper<EnrollmentEntity, EnrollmentDto> {
    @Nonnull MapperUtil mapperUtil;
    @Nonnull CourseMapper courseMapper;
    @Nonnull StudentMapper studentMapper;

    @Nullable
    public EnrollmentEntity toEntity(@Nullable EnrollmentDto enrollmentDto) {
        if (enrollmentDto == null) return null;
        return EnrollmentEntity.builder()
                .id(enrollmentDto.getId())
                .status(enrollmentDto.getStatus())
                .note(enrollmentDto.getNote())
                .course(mapperUtil.map(enrollmentDto.getCourse(), courseMapper::toEntity))
                .student(mapperUtil.map(enrollmentDto.getStudent(), studentMapper::toEntity))
                .build();
    }

    @Nullable
    public EnrollmentDto toDto(@Nullable EnrollmentEntity enrollmentEntity) {
        if (enrollmentEntity == null) return null;
        return EnrollmentDto.builder()
                .id(enrollmentEntity.getId())
                .status(enrollmentEntity.getStatus())
                .note(enrollmentEntity.getNote())
                .course(mapperUtil.map(enrollmentEntity.getCourse(), courseMapper::toDto))
                .student(mapperUtil.map(enrollmentEntity.getStudent(), studentMapper::toDto))
                .date(enrollmentEntity.getEnr_date())
                .build();
    }

    @Override
    public EnrollmentEntity updateEntity(EnrollmentDto dto, EnrollmentEntity entity) {
        entity.setStatus(dto.getStatus());
        return entity;
    }
}
