package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.ClassroomDto;
import com.company.projects.course.coursemanagementsystem.dto.StudentDto;
import com.company.projects.course.coursemanagementsystem.exception.custom.EntityNotFoundException;
import com.company.projects.course.coursemanagementsystem.mapper.ClassroomMapper;
import com.company.projects.course.coursemanagementsystem.mapper.StudentMapper;
import com.company.projects.course.coursemanagementsystem.model.ClassroomEntity;
import com.company.projects.course.coursemanagementsystem.repository.ClassroomRepository;
import com.company.projects.course.coursemanagementsystem.repository.custom.search.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassroomServiceImpl extends BaseServiceImpl<String, ClassroomDto, ClassroomEntity> implements ClassroomService {
    private final ClassroomRepository classroomRepository;
    private final ClassroomMapper classroomMapper;
    private final StudentMapper studentMapper;
    @Autowired
    public ClassroomServiceImpl(ClassroomRepository repository, ClassroomMapper mapper,
                                ClassroomRepository classroomRepository,
                                ClassroomMapper classroomMapper, StudentMapper studentMapper, SearchRepository<ClassroomEntity, String> searchRepository) {
        super(repository, mapper, "Classroom", searchRepository);
        this.classroomRepository = classroomRepository;
        this.classroomMapper = classroomMapper;
        this.studentMapper = studentMapper;
    }

    @Override
    public ClassroomDto addStudent(String id, StudentDto studentDto) {
        ClassroomEntity existingClassroom = classroomRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("Classroom not found with id " + id));
        existingClassroom.getStudents().add(studentMapper.toEntity(studentDto));
        return classroomMapper.toDto(existingClassroom);
    }

    @Override
    public ClassroomDto removeStudent(String id, StudentDto studentDto) {
        ClassroomEntity existingClassroom = classroomRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("Classroom not found with id " + id));
        existingClassroom.getStudents().removeIf(s -> s.getId().equals(studentDto.getId()));
        classroomRepository.save(existingClassroom);
        return classroomMapper.toDto(existingClassroom);
    }
}

