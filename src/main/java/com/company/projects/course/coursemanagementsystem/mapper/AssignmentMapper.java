package com.company.projects.course.coursemanagementsystem.mapper;

import com.company.projects.course.coursemanagementsystem.dto.AssignmentDto;
import com.company.projects.course.coursemanagementsystem.model.AssignmentEntity;
import com.company.projects.course.coursemanagementsystem.util.MapperUtil;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AssignmentMapper implements EntityMapper<AssignmentEntity, AssignmentDto> {
    @Nonnull MapperUtil mapperUtil;
    @Nonnull UserMapper userMapper;
    @Nonnull ClassroomMapper classroomMapper;

    @Nullable
    public AssignmentEntity toEntity (@Nullable AssignmentDto assignmentDto) {
        if (assignmentDto == null) return null;
        return AssignmentEntity.builder()
                .id(assignmentDto.getId())
                .note(assignmentDto.getNote())
                .user(mapperUtil.map(assignmentDto.getUser(), userMapper::toEntity))
                .classroom(mapperUtil.map(assignmentDto.getClassroom(), classroomMapper::toEntity))
                .build();
    }

    @Nullable
    public AssignmentDto toDto (@Nullable AssignmentEntity assignmentEntity) {
        if (assignmentEntity == null) return null;
        return AssignmentDto.builder()
                .id(assignmentEntity.getId())
                .note(assignmentEntity.getNote())
                .user(mapperUtil.map(assignmentEntity.getUser(), userMapper::toDto))
                .classroom(mapperUtil.map(assignmentEntity.getClassroom(), classroomMapper::toDto))
                .build();
    }

    @Override
    public AssignmentEntity updateEntity(AssignmentDto dto, AssignmentEntity entity) {
        return null;
    }
}