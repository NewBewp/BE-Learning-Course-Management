package com.company.projects.course.coursemanagementsystem.mapper;

import com.company.projects.course.coursemanagementsystem.dto.RoleDto;
import com.company.projects.course.coursemanagementsystem.model.RoleEntity;
import com.company.projects.course.coursemanagementsystem.util.MapperUtil;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RoleMapper implements EntityMapper<RoleEntity, RoleDto> {
    @Nonnull MapperUtil mapperUtil;
    @Nonnull AccountMapper accountMapper;
    @Nonnull PermissionMapper permissionMapper;

    @Nullable
    public RoleEntity toEntity(@Nullable RoleDto roleDto) {
        if (roleDto == null) return null;
        return RoleEntity.builder()
                .id(roleDto.getId())
                .name(roleDto.getName())
                .accounts(mapperUtil.mapCollection(roleDto.getAccounts(), accountMapper::toEntity))
                .permissions(mapperUtil.mapCollection(roleDto.getPermissions(), permissionMapper::toEntity))
                .build();
    }

    @Nullable
    public RoleDto toDto(@Nullable RoleEntity roleEntity) {
        if (roleEntity == null) return null;
        return RoleDto.builder()
                .id(roleEntity.getId())
                .name(roleEntity.getName())
                .accounts(mapperUtil.mapCollection(roleEntity.getAccounts(), accountMapper::toDto))
                .permissions(mapperUtil.mapCollection(roleEntity.getPermissions(), permissionMapper::toDto))
                .build();
    }
}
