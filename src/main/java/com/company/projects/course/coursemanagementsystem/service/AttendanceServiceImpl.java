package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.AttendanceDto;
import com.company.projects.course.coursemanagementsystem.mapper.AttendanceMapper;
import com.company.projects.course.coursemanagementsystem.model.AttendanceEntity;
import com.company.projects.course.coursemanagementsystem.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceServiceImpl extends BaseServiceImpl<String, AttendanceDto, AttendanceEntity> implements AttendanceService{
    @Autowired
    public AttendanceServiceImpl(AttendanceRepository repository, AttendanceMapper mapper) {
        super(repository, mapper, "Attendance");
    }
}
