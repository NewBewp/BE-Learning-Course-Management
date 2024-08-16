package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.CourseDto;
import com.company.projects.course.coursemanagementsystem.exception.custom.EmptyResultDataAccessException;
import com.company.projects.course.coursemanagementsystem.mapper.CourseMapper;
import com.company.projects.course.coursemanagementsystem.model.CourseEntity;
import com.company.projects.course.coursemanagementsystem.repository.CourseRepository;
import com.company.projects.course.coursemanagementsystem.service.custom.search.NameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl extends BaseServiceImpl<String, CourseDto, CourseEntity> implements CourseService, NameService<CourseDto> {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Autowired
    public CourseServiceImpl(CourseRepository repository, CourseMapper mapper) {
        super(repository, mapper, "Course");
        this.courseRepository = repository;
        this.courseMapper = mapper;
    }

    @Override
    public Page<CourseDto> searchAllByName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CourseEntity> results = courseRepository.findAllByNameAndDeletedFalse(name, pageable);
        if (results.isEmpty()) throw new EmptyResultDataAccessException("Course" + " not found with name = " + name);
        return results.map(courseMapper::toDto);
    }
}
