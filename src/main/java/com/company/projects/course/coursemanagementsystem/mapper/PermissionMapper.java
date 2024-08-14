package com.company.projects.course.coursemanagementsystem.mapper;

import com.company.projects.course.coursemanagementsystem.dto.PermissionDto;
import com.company.projects.course.coursemanagementsystem.model.PermissionEntity;
import com.company.projects.course.coursemanagementsystem.util.MapperUtil;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PermissionMapper implements EntityMapper<PermissionEntity, PermissionDto> {
    @Nonnull MapperUtil mapperUtil;
    @Nonnull RoleMapper roleMapper;

    @Nullable
    public PermissionEntity toEntity(@Nullable PermissionDto permissionDto) {
        if (permissionDto == null) return null;
        return PermissionEntity.builder()
                .id(permissionDto.getId())
                .name(permissionDto.getName())
                .build();
    }

    @Nullable
    public PermissionDto toDto(@Nullable PermissionEntity permissionEntity) {
        if (permissionEntity == null) return null;
        return PermissionDto.builder()
                .id(permissionEntity.getId())
                .name(permissionEntity.getName())
                .build();
    }
}
