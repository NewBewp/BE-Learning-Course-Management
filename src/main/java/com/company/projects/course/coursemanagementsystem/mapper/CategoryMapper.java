package com.company.projects.course.coursemanagementsystem.mapper;

import com.company.projects.course.coursemanagementsystem.dto.CategoryDto;
import com.company.projects.course.coursemanagementsystem.model.CategoryEntity;
import com.company.projects.course.coursemanagementsystem.util.MapperUtil;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CategoryMapper implements EntityMapper<CategoryEntity, CategoryDto> {
    @Nonnull MapperUtil mapperUtil;
    @Nonnull CourseMapper courseMapper;

    @Nullable
    public CategoryEntity toEntity(@Nullable CategoryDto categoryDto) {
        if (categoryDto == null) return null;
        return CategoryEntity.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
//                .courses(mapperUtil.mapCollection(categoryDto.getCourses(), courseMapper::toEntity))
                .build();
    }

    @Nullable
    public CategoryDto toDto(@Nullable CategoryEntity categoryEntity) {
        if (categoryEntity == null) return null;
        return CategoryDto.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
//                .courses(mapperUtil.mapCollection(categoryEntity.getCourses(), courseMapper::toDto))
                .build();
    }

    @Override
    public CategoryEntity updateEntity(CategoryDto dto, CategoryEntity entity) {
        entity.setName(dto.getName());
        return entity;
    }
}
