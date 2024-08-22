package com.company.projects.course.coursemanagementsystem.controller;

import com.company.projects.course.coursemanagementsystem.dto.CourseDto;
import com.company.projects.course.coursemanagementsystem.service.CloudinaryService;
import com.company.projects.course.coursemanagementsystem.service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/courses")
public class CourseControllerImpl extends BaseControllerImpl<String, CourseDto, CourseService> implements CourseController {
    private final CourseService courseService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    public CourseControllerImpl(CourseService service, CloudinaryService cloudinaryService) {
        super(service);
        this.courseService = service;
    }

    @Override
    @GetMapping("/search")
    public ResponseEntity<Page<CourseDto>> search(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "updatedAt:desc") String sort) {
        Page<CourseDto> results = courseService.search(name, page, size, sort);
        return ResponseEntity.ok(results);
    }

    @Override
    @GetMapping("/filter")
    public ResponseEntity<Page<CourseDto>> filter(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) String categoryId,
            @RequestParam(required = false) String companyId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "updatedAt:desc") String sort) {
        Page<CourseDto> results = courseService.filter(startDate, endDate, categoryId, companyId, page, size, sort);
        return ResponseEntity.ok(results);
    }

    @PostMapping(value = "/post_image", consumes = {"multipart/form-data"})
    public ResponseEntity<CourseDto> createCourse(
            @RequestPart("course") String courseJson,
            @RequestPart(value = "image", required = false) MultipartFile image) {
        try {
            CourseDto courseDto = objectMapper.readValue(courseJson, CourseDto.class);
            courseDto.setImage(image);
            CourseDto createdDto = courseService.save(courseDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
