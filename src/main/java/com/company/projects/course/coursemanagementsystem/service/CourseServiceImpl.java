package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.CourseDto;
import com.company.projects.course.coursemanagementsystem.exception.custom.EmptyResultDataAccessException;
import com.company.projects.course.coursemanagementsystem.mapper.CourseMapper;
import com.company.projects.course.coursemanagementsystem.model.CourseEntity;
import com.company.projects.course.coursemanagementsystem.repository.CourseRepository;
import com.company.projects.course.coursemanagementsystem.service.custom.search.NameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

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
    public Collection<CourseDto> searchAllByName(String name) {
        Collection<CourseEntity> results = courseRepository.findAllByNameAndDeletedFalse(name);
        if (results.isEmpty()) throw new EmptyResultDataAccessException("Course" + " not found with name = " + name);
        return results.stream().map(courseMapper::toDto).toList();
    }
}
