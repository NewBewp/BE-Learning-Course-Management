package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.CourseDto;
import com.company.projects.course.coursemanagementsystem.dto.StudentDto;
import com.company.projects.course.coursemanagementsystem.exception.custom.EmptyResultDataAccessException;
import com.company.projects.course.coursemanagementsystem.exception.custom.EntityNotFoundException;
import com.company.projects.course.coursemanagementsystem.mapper.CourseMapper;
import com.company.projects.course.coursemanagementsystem.mapper.StudentMapper;
import com.company.projects.course.coursemanagementsystem.model.CourseEntity;
import com.company.projects.course.coursemanagementsystem.model.EnrollmentEntity;
import com.company.projects.course.coursemanagementsystem.repository.CourseRepository;
import com.company.projects.course.coursemanagementsystem.repository.EnrollmentRepository;
import com.company.projects.course.coursemanagementsystem.repository.specification.CourseSpecification;
import com.company.projects.course.coursemanagementsystem.repository.specification.EnrollmentSpecification;
import com.company.projects.course.coursemanagementsystem.util.JPAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

@Service
public class CourseServiceImpl extends BaseServiceImpl<String, CourseDto, CourseEntity> implements CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final CloudinaryService cloudinaryService;
    private final EnrollmentRepository enrollmentRepository;
    private final StudentMapper studentMapper;

    @Autowired
    public CourseServiceImpl(CourseRepository repository, CourseMapper mapper,
                             CloudinaryService cloudinaryService,
                             EnrollmentRepository enrollmentRepository,
                             StudentMapper studentMapper) {
        super(repository, mapper, "Course");
        this.courseRepository = repository;
        this.courseMapper = mapper;
        this.cloudinaryService = cloudinaryService;
        this.enrollmentRepository = enrollmentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public Page<CourseDto> search(String name, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, JPAUtil.getSortRequestParam(sort));
        Page<CourseEntity> results = courseRepository.findAllByNameAndDeletedFalse(name, pageable);
        if (results.isEmpty()) throw new EmptyResultDataAccessException("Course" + " not found with name = " + name);
        return results.map(courseMapper::toDto);
    }

    @Override
    public Page<CourseDto> filter(LocalDate startDate, LocalDate endDate, String categoryId, String companyId, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, JPAUtil.getSortRequestParam(sort));
        Specification<CourseEntity> spec = CourseSpecification.filterByCriteria(startDate, endDate, categoryId, companyId);
        Page<CourseEntity> results = courseRepository.findAll(spec, pageable);
        if (results.isEmpty()) throw new EmptyResultDataAccessException("No results found");
        return results.map(courseMapper::toDto);
    }

    @Override
    public CourseDto save(CourseDto courseDto) {
        CourseEntity courseEntity = courseMapper.toEntity(courseDto);
        MultipartFile image = courseDto.getImage();
        if (image != null && !image.isEmpty()) {
            try {
                String imageUrl = cloudinaryService.uploadFile(image);
                courseEntity.setImageUrl(imageUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        CourseEntity savedEntity = courseRepository.save(courseEntity);
        return courseMapper.toDto(savedEntity);
    }

    @Override
    public void updateImage(String id, MultipartFile image) {
        CourseEntity courseEntity = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
        if (image != null && !image.isEmpty()) {
            try {
                String imageUrl = cloudinaryService.uploadFile(image);
                courseEntity.setImageUrl(imageUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        courseRepository.save(courseEntity);
    }

    @Override
    public Page<StudentDto> getAllStudentEnrollCourseApproved(String id, int page, int size, String sort) {
        Specification<EnrollmentEntity> spec = EnrollmentSpecification.filter("APPROVED", null, id, null);
        Pageable pageable = PageRequest.of(page, size, JPAUtil.getSortRequestParam(sort));
        Page<EnrollmentEntity> enrollments = enrollmentRepository.findAll(spec, pageable);
        if (enrollments.isEmpty()) throw new EmptyResultDataAccessException("No results found");
        return enrollments.map( e -> studentMapper.toDto(e.getStudent()));
    }
}
