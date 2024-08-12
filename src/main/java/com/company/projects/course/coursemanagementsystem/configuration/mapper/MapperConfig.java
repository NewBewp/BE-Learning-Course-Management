package com.company.projects.course.coursemanagementsystem.configuration.mapper;

import com.company.projects.course.coursemanagementsystem.mapper.*;
import com.company.projects.course.coursemanagementsystem.util.MapperUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class MapperConfig {
    @Bean
    public MapperUtil mapperUtil() {return new MapperUtil();}

    @Bean
    @Lazy
    public AccountMapper accountMapper(@Lazy MapperUtil mapperUtil, @Lazy RoleMapper roleMapper, @Lazy UserMapper userMapper) {
        return new AccountMapper(mapperUtil, roleMapper, userMapper);
    }

    @Bean
    @Lazy
    public RoleMapper roleMapper(@Lazy MapperUtil mapperUtil, @Lazy AccountMapper accountMapper, @Lazy PermissionMapper permissionMapper) {
        return new RoleMapper(mapperUtil, accountMapper, permissionMapper);
    }

    @Bean
    @Lazy
    public AssignmentMapper assignmentMapper(@Lazy MapperUtil mapperUtil, @Lazy UserMapper userMapper, @Lazy ClassroomMapper classroomMapper ) {
        return new AssignmentMapper(mapperUtil, userMapper, classroomMapper);
    }

    @Bean
    @Lazy
    public AttendanceMapper attendanceMapper(@Lazy MapperUtil mapperUtil, @Lazy ClassroomMapper classroomMapper, @Lazy StudentMapper studentMapper, @Lazy DateMapper dateMapper) {
        return new AttendanceMapper(mapperUtil, classroomMapper, studentMapper, dateMapper);
    }

    @Bean
    @Lazy
    public CategoryMapper categoryMapper(@Lazy MapperUtil mapperUtil, @Lazy CourseMapper courseMapper) {
        return new CategoryMapper(mapperUtil, courseMapper);
    }

    @Bean
    @Lazy
    public ClassroomMapper classroomMapper(@Lazy MapperUtil mapperUtil, @Lazy CourseMapper courseMapper, @Lazy AssignmentMapper assignmentMapper, @Lazy StudentMapper studentMapper, @Lazy AttendanceMapper attendanceMapper) {
        return new ClassroomMapper(mapperUtil, courseMapper, assignmentMapper, studentMapper, attendanceMapper);
    }

    @Bean
    @Lazy
    public CompanyMapper companyMapper(@Lazy MapperUtil mapperUtil, @Lazy CourseMapper courseMapper) {
        return new CompanyMapper(mapperUtil, courseMapper);
    }

    @Bean
    @Lazy
    public CourseMapper courseMapper(@Lazy MapperUtil mapperUtil, @Lazy CategoryMapper categoryMapper, @Lazy CompanyMapper companyMapper, @Lazy EnrollmentMapper enrollmentMapper, @Lazy ClassroomMapper classroomMapper) {
        return new CourseMapper(mapperUtil, categoryMapper, companyMapper, enrollmentMapper, classroomMapper);
    }

    @Bean
    @Lazy
    public DateMapper dateMapper(@Lazy MapperUtil mapperUtil, @Lazy EnrollmentMapper enrollmentMapper, @Lazy AttendanceMapper attendanceMapper) {
        return new DateMapper(mapperUtil, enrollmentMapper, attendanceMapper);
    }

    @Bean
    @Lazy
    public EnrollmentMapper enrollmentMapper(@Lazy MapperUtil mapperUtil, @Lazy CourseMapper courseMapper, @Lazy StudentMapper studentMapper, @Lazy DateMapper dateMapper) {
        return new EnrollmentMapper(mapperUtil, courseMapper, studentMapper, dateMapper);
    }

    @Bean
    @Lazy
    public PermissionMapper permissionMapper(@Lazy MapperUtil mapperUtil, @Lazy RoleMapper roleMapper) {
        return new PermissionMapper(mapperUtil, roleMapper);
    }

    @Bean
    @Lazy
    public StudentMapper studentMapper(@Lazy MapperUtil mapperUtil, @Lazy ClassroomMapper classroomMapper, @Lazy EnrollmentMapper enrollmentMapper, @Lazy AttendanceMapper attendanceMapper) {
        return new StudentMapper(mapperUtil, classroomMapper, enrollmentMapper, attendanceMapper);
    }

    @Bean
    @Lazy
    public UserMapper userMapper(@Lazy MapperUtil mapperUtil, @Lazy AccountMapper accountMapper, @Lazy AssignmentMapper assignmentMapper) {
        return new UserMapper(mapperUtil, accountMapper, assignmentMapper);
    }
}
