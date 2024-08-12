package com.company.projects.course.coursemanagementsystem.mapper;

import com.company.projects.course.coursemanagementsystem.dto.CourseDto;
import com.company.projects.course.coursemanagementsystem.model.CourseEntity;
import com.company.projects.course.coursemanagementsystem.util.MapperUtil;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CourseMapper implements EntityMapper<CourseEntity, CourseDto> {
    @Nonnull MapperUtil mapperUtil;
    @Nonnull CategoryMapper categoryMapper;
    @Nonnull CompanyMapper companyMapper;
    @Nonnull EnrollmentMapper enrollmentMapper;
    @Nonnull ClassroomMapper classroomMapper;

    @Nullable
    public CourseEntity toEntity (@Nullable CourseDto courseDto) {
        if (courseDto == null) return null;
        return CourseEntity.builder()
                .id(courseDto.getId())
                .name(courseDto.getName())
                .description(courseDto.getDescription())
                .startDate(courseDto.getStartDate())
                .endDate(courseDto.getEndDate())
                .category(mapperUtil.map(courseDto.getCategory(), categoryMapper::toEntity))
                .company(mapperUtil.map(courseDto.getCompany(), companyMapper::toEntity))
                .enrollments(mapperUtil.mapCollection(courseDto.getEnrollments(), enrollmentMapper::toEntity))
                .classrooms(mapperUtil.mapCollection(courseDto.getClassrooms(), classroomMapper::toEntity))
                .build();
    }

    @Nullable
    public CourseDto toDto (@Nullable CourseEntity courseEntity) {
        if (courseEntity == null) return null;
        return CourseDto.builder()
                .id(courseEntity.getId())
                .name(courseEntity.getName())
                .description(courseEntity.getDescription())
                .startDate(courseEntity.getStartDate())
                .endDate(courseEntity.getEndDate())
                .category(mapperUtil.map(courseEntity.getCategory(), categoryMapper::toDto))
                .company(mapperUtil.map(courseEntity.getCompany(), companyMapper::toDto))
                .enrollments(mapperUtil.mapCollection(courseEntity.getEnrollments(), enrollmentMapper::toDto))
                .classrooms(mapperUtil.mapCollection(courseEntity.getClassrooms(), classroomMapper::toDto))
                .build();
    }
}
