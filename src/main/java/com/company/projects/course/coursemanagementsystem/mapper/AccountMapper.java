package com.company.projects.course.coursemanagementsystem.mapper;

import com.company.projects.course.coursemanagementsystem.dto.AccountDto;
import com.company.projects.course.coursemanagementsystem.model.AccountEntity;
import com.company.projects.course.coursemanagementsystem.util.MapperUtil;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AccountMapper implements EntityMapper<AccountEntity, AccountDto> {
    @Nonnull MapperUtil mapperUtil;
    @Nonnull RoleMapper roleMapper;
    @Nonnull UserMapper userMapper;

    @Nullable
    public AccountEntity toEntity (@Nullable AccountDto accountDto) {
        if (accountDto == null) return null;
        return AccountEntity.builder()
                .id(accountDto.getId())
                .user(mapperUtil.map(accountDto.getUser(), userMapper::toEntity))
                .role(mapperUtil.map(accountDto.getRole(), roleMapper::toEntity))
                .build();
    }

    @Nullable
    public AccountDto toDto (@Nullable AccountEntity accountEntity) {
        if (accountEntity == null) return null;
        return AccountDto.builder()
                .id(accountEntity.getId())
                .username(accountEntity.getUsername())
                .user(mapperUtil.map(accountEntity.getUser(), userMapper::toDto))
                .role(mapperUtil.map(accountEntity.getRole(), roleMapper::toDto))
                .build();
    }
}
