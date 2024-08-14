package com.company.projects.course.coursemanagementsystem.mapper;

import com.company.projects.course.coursemanagementsystem.dto.StudentDto;
import com.company.projects.course.coursemanagementsystem.model.StudentEntity;
import com.company.projects.course.coursemanagementsystem.util.MapperUtil;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class StudentMapper implements EntityMapper<StudentEntity, StudentDto> {
    @Nonnull MapperUtil mapperUtil;
    @Nonnull ClassroomMapper classroomMapper;
    @Nonnull EnrollmentMapper enrollmentMapper;
    @Nonnull AttendanceMapper attendanceMapper;

    @Nullable
    public StudentEntity toEntity(@Nullable StudentDto studentDto) {
        if (studentDto == null) return null;
        return StudentEntity.builder()
                .id(studentDto.getId())
                .name(studentDto.getName())
                .gender(studentDto.getGender())
                .birthday(studentDto.getBirthday())
                .phone(studentDto.getPhone())
                .email(studentDto.getEmail())
                .address(studentDto.getAddress())
//                .enrollments(mapperUtil.mapCollection(studentDto.getEnrollments(), enrollmentMapper::toEntity))
//                .classrooms((mapperUtil.mapCollection(studentDto.getClassrooms(), classroomMapper::toEntity)))
//                .attendances(mapperUtil.mapCollection(studentDto.getAttendances(), attendanceMapper::toEntity))
                .build();
    }

    @Nullable
    public StudentDto toDto(@Nullable StudentEntity studentEntity) {
        if (studentEntity == null) return null;
        return StudentDto.builder()
                .id(studentEntity.getId())
                .name(studentEntity.getName())
                .gender(studentEntity.getGender())
                .birthday(studentEntity.getBirthday())
                .phone(studentEntity.getPhone())
                .email(studentEntity.getEmail())
                .address(studentEntity.getAddress())
//                .enrollments(mapperUtil.mapCollection(studentEntity.getEnrollments(), enrollmentMapper::toDto))
//                .classrooms(mapperUtil.mapCollection(studentEntity.getClassrooms(), classroomMapper::toDto))
//                .attendances(mapperUtil.mapCollection(studentEntity.getAttendances(), attendanceMapper::toDto))
                .build();
    }
}
