package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.AttendanceDto;
import com.company.projects.course.coursemanagementsystem.exception.custom.EmptyResultDataAccessException;
import com.company.projects.course.coursemanagementsystem.mapper.AttendanceMapper;
import com.company.projects.course.coursemanagementsystem.model.AttendanceEntity;
import com.company.projects.course.coursemanagementsystem.repository.AttendanceRepository;
import com.company.projects.course.coursemanagementsystem.repository.specification.AttendanceSpecification;
import com.company.projects.course.coursemanagementsystem.util.JPAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AttendanceServiceImpl extends BaseServiceImpl<String, AttendanceDto, AttendanceEntity> implements AttendanceService{
    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;
    @Autowired
    public AttendanceServiceImpl(AttendanceRepository repository, AttendanceMapper mapper) {
        super(repository, mapper, "Attendance");
        this.attendanceRepository = repository;
        this.attendanceMapper = mapper;
    }

    @Override
    public Page<AttendanceDto> filter(Boolean status, LocalDate date, String studentId, String classroomId, int page, int size, String sort) {
        Sort sortOrder = JPAUtil.getSortRequestParam(sort);
        Pageable pageable = PageRequest.of(page, size, sortOrder);
        Specification<AttendanceEntity> spec = AttendanceSpecification.filterByCriteria(status, date, studentId, classroomId);
        Page<AttendanceEntity> results = attendanceRepository.findAll(spec, pageable);
        if (results.isEmpty()) {
            throw new EmptyResultDataAccessException("No results found");
        }
        return results.map(attendanceMapper::toDto);
    }
}
