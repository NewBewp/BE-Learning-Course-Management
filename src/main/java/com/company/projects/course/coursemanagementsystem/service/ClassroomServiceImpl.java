package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.ClassroomDto;
import com.company.projects.course.coursemanagementsystem.mapper.ClassroomMapper;
import com.company.projects.course.coursemanagementsystem.model.ClassroomEntity;
import com.company.projects.course.coursemanagementsystem.repository.ClassroomRepository;
import org.springframework.stereotype.Service;

@Service
public class ClassroomServiceImpl extends BaseServiceImpl<String, ClassroomDto, ClassroomEntity> implements ClassroomService {
    public ClassroomServiceImpl(ClassroomRepository repository, ClassroomMapper mapper) {
        super(repository, mapper, "Classroom");
    }
}
