package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.ClassroomDto;
import com.company.projects.course.coursemanagementsystem.dto.CreateClassroomAutoDto;
import com.company.projects.course.coursemanagementsystem.dto.StudentDto;
import com.company.projects.course.coursemanagementsystem.exception.custom.EmptyResultDataAccessException;
import com.company.projects.course.coursemanagementsystem.exception.custom.EntityNotFoundException;
import com.company.projects.course.coursemanagementsystem.mapper.ClassroomMapper;
import com.company.projects.course.coursemanagementsystem.mapper.CourseMapper;
import com.company.projects.course.coursemanagementsystem.mapper.StudentMapper;
import com.company.projects.course.coursemanagementsystem.model.ClassroomEntity;
import com.company.projects.course.coursemanagementsystem.model.StudentEntity;
import com.company.projects.course.coursemanagementsystem.repository.ClassroomRepository;
import com.company.projects.course.coursemanagementsystem.repository.CourseRepository;
import com.company.projects.course.coursemanagementsystem.repository.StudentRepository;
import com.company.projects.course.coursemanagementsystem.repository.specification.ClassroomSpecification;
import com.company.projects.course.coursemanagementsystem.repository.specification.StudentSpecification;
import com.company.projects.course.coursemanagementsystem.util.JPAUtil;
import com.company.projects.course.coursemanagementsystem.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ClassroomServiceImpl extends BaseServiceImpl<String, ClassroomDto, ClassroomEntity> implements ClassroomService {
    private final ClassroomRepository classroomRepository;
    private final ClassroomMapper classroomMapper;
    private final StudentMapper studentMapper;
    private final StudentSpecification studentSpecification;
    private final StudentRepository studentRepository;
    private final CourseMapper courseMapper;
    private final MapperUtil mapperUtil;

    @Autowired
    public ClassroomServiceImpl(ClassroomRepository repository, ClassroomMapper mapper,
                                ClassroomRepository classroomRepository,
                                ClassroomMapper classroomMapper, StudentMapper studentMapper,
                                StudentSpecification studentSpecification,
                                StudentRepository studentRepository, CourseRepository courseRepository,
                                CourseMapper courseMapper, MapperUtil mapperUtil) {
        super(repository, mapper, "Classroom");
        this.classroomRepository = classroomRepository;
        this.classroomMapper = classroomMapper;
        this.studentMapper = studentMapper;
        this.studentSpecification = studentSpecification;
        this.studentRepository = studentRepository;
        this.courseMapper = courseMapper;
        this.mapperUtil = mapperUtil;
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

    @Override
    public Page<ClassroomDto> search(String name, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, JPAUtil.getSortRequestParam(sort));
        Page<ClassroomEntity> results = classroomRepository.findAllByNameAndDeletedFalse(name, pageable);
        if (results.isEmpty()) throw new EmptyResultDataAccessException("Classroom" + " not found with name = " + name);
        return results.map(classroomMapper::toDto);
    }

    @Override
    public Page<ClassroomDto> filter(String courseId, String studentId, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, JPAUtil.getSortRequestParam(sort));
        Specification<ClassroomEntity> spec = ClassroomSpecification.filterByCriteria(courseId, studentId);
        Page<ClassroomEntity> results = classroomRepository.findAll(spec, pageable);
        if (results.isEmpty()) throw new EmptyResultDataAccessException("No results found");
        return results.map(classroomMapper::toDto);
    }

    @Override
    public void createClassRoomAndAddStudentAuto(String courseId, Integer size) {
        Specification<StudentEntity> spec = studentSpecification.filterByCriteria(courseId, "PENDING");
        Collection<StudentEntity> students = studentRepository.findAll(spec);
    }

    @Override
    public void createClassroomAuto(CreateClassroomAutoDto createClassroomAutoDto) {
        String courseId = createClassroomAutoDto.getCourse().getId();
        Integer size = createClassroomAutoDto.getSize();

        // Lấy danh sách các lớp hiện tại
        Specification<ClassroomEntity> specClassroom = ClassroomSpecification.filterByCriteria(courseId, null);
        Collection<ClassroomEntity> classroomCurrents = classroomRepository.findAll(specClassroom);

        // Lấy danh sách sinh viên đang chờ
        Specification<StudentEntity> specStudent = studentSpecification.filterByCriteria(courseId, "PENDING");
        Collection<StudentEntity> students = studentRepository.findAll(specStudent);

        // Tính toán số lớp cần tạo
        int totalClassroomWillCreate = (int) Math.ceil((double) students.size() / size);
        int sttClassroomEnd = classroomCurrents.size() + 1;

        List<String> classNameList = new ArrayList<>();
        StringBuilder className = new StringBuilder("Classroom ");
        int baseLength = className.length();

        // Tạo tên lớp mới
        while (classNameList.size() < totalClassroomWillCreate) {
            sttClassroomEnd++;
            className.setLength(baseLength);
            className.append(sttClassroomEnd);

            if (!classroomRepository.existsByName(className.toString())) {
                classNameList.add(className.toString());
            }
        }

        // Lưu các lớp mới vào cơ sở dữ liệu
        for (String classNameStr : classNameList) {
            ClassroomEntity newClassroom = ClassroomEntity.builder()
                    .name(classNameStr)
                    .course(mapperUtil.map(createClassroomAutoDto.getCourse(), courseMapper::toEntity))
                    .build();
            classroomRepository.save(newClassroom);
        }
    }
}

