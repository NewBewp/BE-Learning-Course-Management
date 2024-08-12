package com.company.projects.course.coursemanagementsystem.mapper;

import com.company.projects.course.coursemanagementsystem.dto.DateDto;
import com.company.projects.course.coursemanagementsystem.model.DateEntity;
import com.company.projects.course.coursemanagementsystem.util.MapperUtil;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class DateMapper implements EntityMapper<DateEntity, DateDto> {
    @Nonnull MapperUtil mapperUtil;
    @Nonnull EnrollmentMapper enrollmentMapper;
    @Nonnull AttendanceMapper attendanceMapper;

    @Nullable
    public DateEntity toEntity(@Nullable DateDto dateDto) {
        if (dateDto == null) return null;
        return DateEntity.builder()
                .date(dateDto.getDate())
                .enrollments(mapperUtil.mapCollection(dateDto.getEnrollments(), enrollmentMapper::toEntity))
                .attendances(mapperUtil.mapCollection(dateDto.getAttendances(), attendanceMapper::toEntity))
                .build();
    }

    @Nullable
    public DateDto toDto(@Nullable DateEntity dateEntity) {
        if (dateEntity == null) return null;
        return DateDto.builder()
                .date(dateEntity.getDate())
                .enrollments(mapperUtil.mapCollection(dateEntity.getEnrollments(), enrollmentMapper::toDto))
                .attendances(mapperUtil.mapCollection(dateEntity.getAttendances(), attendanceMapper::toDto))
                .build();
    }
}
