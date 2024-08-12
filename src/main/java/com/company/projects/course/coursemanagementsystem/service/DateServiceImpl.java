package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.DateDto;
import com.company.projects.course.coursemanagementsystem.mapper.DateMapper;
import com.company.projects.course.coursemanagementsystem.model.DateEntity;
import com.company.projects.course.coursemanagementsystem.repository.DateRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DateServiceImpl extends BaseServiceImpl<LocalDate, DateDto, DateEntity> implements DateService{
    public DateServiceImpl(DateRepository repository, DateMapper mapper) {
        super(repository, mapper, "Date");
    }
}
