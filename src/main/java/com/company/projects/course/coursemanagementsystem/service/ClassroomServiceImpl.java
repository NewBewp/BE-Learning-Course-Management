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
import com.company.projects.course.coursemanagementsystem.model.EnrollmentEntity;
import com.company.projects.course.coursemanagementsystem.model.StudentEntity;
import com.company.projects.course.coursemanagementsystem.repository.ClassroomRepository;
import com.company.projects.course.coursemanagementsystem.repository.CourseRepository;
import com.company.projects.course.coursemanagementsystem.repository.EnrollmentRepository;
import com.company.projects.course.coursemanagementsystem.repository.StudentRepository;
import com.company.projects.course.coursemanagementsystem.repository.specification.ClassroomSpecification;
import com.company.projects.course.coursemanagementsystem.repository.specification.EnrollmentSpecification;
import com.company.projects.course.coursemanagementsystem.repository.specification.StudentSpecification;
import com.company.projects.course.coursemanagementsystem.util.JPAUtil;
import com.company.projects.course.coursemanagementsystem.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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
    private final CourseMapper courseMapper;
    private final MapperUtil mapperUtil;
    private final EnrollmentRepository enrollmentRepository;
    private final CurrentUserService currentUserService;

    @Autowired
    public ClassroomServiceImpl(ClassroomRepository repository, ClassroomMapper mapper,
                                ClassroomRepository classroomRepository,
                                ClassroomMapper classroomMapper, StudentMapper studentMapper,
                                CourseMapper courseMapper, MapperUtil mapperUtil,
                                EnrollmentRepository enrollmentRepository, CurrentUserService currentUserService) {
        super(repository, mapper, "Classroom");
        this.classroomRepository = classroomRepository;
        this.classroomMapper = classroomMapper;
        this.studentMapper = studentMapper;
        this.courseMapper = courseMapper;
        this.mapperUtil = mapperUtil;
        this.enrollmentRepository = enrollmentRepository;
        this.currentUserService = currentUserService;
    }

    public Page<ClassroomEntity> filterByCompany(Page<ClassroomEntity> results, Pageable pageable) {
        if (currentUserService.getCurrentUserRole().equals("ROLE_admin")) return results;
        List<ClassroomEntity> filteredResults = results.stream()
                .filter(e -> e.getCourse().getCompany().getId().equals(currentUserService.getCurrentUserDetails().getCompanyId())).toList();

        if (filteredResults.isEmpty()) {
            throw new EmptyResultDataAccessException("Classroom is empty");
        }

        return new PageImpl<>(filteredResults, pageable, results.getTotalElements());
    }

    @Override
    public Page<ClassroomDto> findAll(int page, int size, String sort) {
        Sort sortBy = JPAUtil.getSortRequestParam(sort);
        Pageable pageable = PageRequest.of(page, size, sortBy);
        Page<ClassroomEntity> results = classroomRepository.findAllByDeletedFalse(pageable);
        if (results.isEmpty()) throw new EmptyResultDataAccessException("Classroom" + " is empty");
        return filterByCompany(results, pageable).map(classroomMapper::toDto);
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
        return filterByCompany(results, pageable).map(classroomMapper::toDto);
    }

    @Override
    public Page<ClassroomDto> filter(String courseId, String studentId, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, JPAUtil.getSortRequestParam(sort));
        Specification<ClassroomEntity> spec = ClassroomSpecification.filterByCriteria(courseId, studentId);
        Page<ClassroomEntity> results = classroomRepository.findAll(spec, pageable);
        if (results.isEmpty()) throw new EmptyResultDataAccessException("No results found");
        return filterByCompany(results, pageable).map(classroomMapper::toDto);
    }

    @Override
    public void createClassroomAuto(CreateClassroomAutoDto createClassroomAutoDto) {
        String courseId = createClassroomAutoDto.getCourse().getId();
        Integer size = createClassroomAutoDto.getSize();

        // Lấy danh sách các lớp hiện tại
        Specification<ClassroomEntity> specClassroom = ClassroomSpecification.filterByCriteria(courseId, null);
        Collection<ClassroomEntity> classroomCurrents = classroomRepository.findAll(specClassroom);
        // Lấy danh sách sinh viên đang chờ
        Specification<EnrollmentEntity> specEnrollment = EnrollmentSpecification.filterByCriteria(courseId, "APPROVED");
        Collection<EnrollmentEntity> enrollments = enrollmentRepository.findAll(specEnrollment);
        List<StudentEntity> students = new ArrayList<>();
        enrollments.forEach(e -> students.add(e.getStudent()));
        // Tính toán số lớp cần tạo
        int totalClassroomWillCreate = (int) Math.ceil((double) enrollments.size() / size);
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

            // Kiểm tra kích thước của danh sách students
            if (students.size() < size) {
                break;
            }

            // Lấy subList và tạo ClassroomEntity
            Collection<StudentEntity> subStudent = new ArrayList<>(students.subList(0, size));
            ClassroomEntity newClassroom = ClassroomEntity.builder()
                    .name(classNameStr)
                    .course(mapperUtil.map(createClassroomAutoDto.getCourse(), courseMapper::toEntity))
                    .students(subStudent)
                    .build();
            classroomRepository.save(newClassroom);

            // Xóa các phần tử đã sử dụng khỏi danh sách gốc
            students.subList(0, size).clear();
        }
    }
}

