package com.company.projects.course.coursemanagementsystem.mapper;

import com.company.projects.course.coursemanagementsystem.dto.ClassroomDto;
import com.company.projects.course.coursemanagementsystem.model.ClassroomEntity;
import com.company.projects.course.coursemanagementsystem.util.MapperUtil;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ClassroomMapper implements EntityMapper<ClassroomEntity, ClassroomDto> {
    @Nonnull MapperUtil mapperUtil;
    @Nonnull CourseMapper courseMapper;
    @Nonnull AssignmentMapper assignmentMapper;
    @Nonnull StudentMapper studentMapper;
    @Nonnull AttendanceMapper attendanceMapper;

    @Nullable
    public ClassroomEntity toEntity(@Nullable ClassroomDto classroomDto) {
        if (classroomDto == null) return null;
        return ClassroomEntity.builder()
                .id(classroomDto.getId())
                .name(classroomDto.getName())
                .course(mapperUtil.map(classroomDto.getCourse(), courseMapper::toEntity))
//                .assignments(mapperUtil.mapCollection(classroomDto.getAssignments(), assignmentMapper::toEntity))
                .students(mapperUtil.mapCollection(classroomDto.getStudents(), studentMapper::toEntity))
//                .attendances(mapperUtil.mapCollection(classroomDto.getAttendances(), attendanceMapper::toEntity))
                .build();
    }

    @Nullable
    public ClassroomDto toDto(@Nullable ClassroomEntity classroomEntity) {
        if (classroomEntity == null) return null;
        return ClassroomDto.builder()
                .id(classroomEntity.getId())
                .name(classroomEntity.getName())
                .course(mapperUtil.map(classroomEntity.getCourse(), courseMapper::toDto))
//                .assignments(mapperUtil.mapCollection(classroomEntity.getAssignments(), assignmentMapper::toDto))
                .students(mapperUtil.mapCollection(classroomEntity.getStudents(), studentMapper::toDto))
//                .attendances(mapperUtil.mapCollection(classroomEntity.getAttendances(), attendanceMapper::toDto))
                .build();
    }
}
