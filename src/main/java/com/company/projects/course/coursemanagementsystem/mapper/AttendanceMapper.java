package com.company.projects.course.coursemanagementsystem.mapper;

import com.company.projects.course.coursemanagementsystem.dto.AttendanceDto;
import com.company.projects.course.coursemanagementsystem.model.AttendanceEntity;
import com.company.projects.course.coursemanagementsystem.util.MapperUtil;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AttendanceMapper implements EntityMapper<AttendanceEntity, AttendanceDto> {
    @Nonnull MapperUtil mapperUtil;
    @Nonnull ClassroomMapper classroomMapper;
    @Nonnull StudentMapper studentMapper;

    @Nullable
    public AttendanceEntity toEntity(@Nullable AttendanceDto attendanceDto) {
        if (attendanceDto == null) return null;
        return AttendanceEntity.builder()
                .id(attendanceDto.getId())
                .status(attendanceDto.getStatus())
                .reason(attendanceDto.getReason())
                .classroom(mapperUtil.map(attendanceDto.getClassroom(), classroomMapper::toEntity))
                .student(mapperUtil.map(attendanceDto.getStudent(), studentMapper::toEntity))
                .build();
    }

    @Nullable
    public AttendanceDto toDto(@Nullable AttendanceEntity attendanceEntity) {
        if (attendanceEntity == null) return null;
        return AttendanceDto.builder()
                .id(attendanceEntity.getId())
                .status(attendanceEntity.getStatus())
                .reason(attendanceEntity.getReason())
                .classroom(mapperUtil.map(attendanceEntity.getClassroom(), classroomMapper::toDto))
                .student(mapperUtil.map(attendanceEntity.getStudent(), studentMapper::toDto))
                .date(attendanceEntity.getDate())
                .build();
    }
}
