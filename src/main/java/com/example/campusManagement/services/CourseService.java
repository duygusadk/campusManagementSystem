package com.example.campusManagement.services;

import com.example.campusManagement.dto.request.CourseRequestDto;
import com.example.campusManagement.dto.response.CourseResponseDto;
import com.example.campusManagement.models.Course;
import com.example.campusManagement.models.Department;
import com.example.campusManagement.repo.CourseRepo;
import com.example.campusManagement.repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService extends BaseService<Course, CourseRepo> {

    @Autowired
    private DepartmentRepo departmentRepo;

    public CourseService(CourseRepo repository) {
        super(repository);
    }

    public List<CourseResponseDto> findAllDto() {
        return findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public CourseResponseDto findByIdDto(Long id) {
        return toDto(findById(id));
    }

    public CourseResponseDto create(CourseRequestDto dto) {
        Course course = new Course(
                dto.getTitle(),
                dto.getDescription(),
                dto.getCredits()
        );
        if (dto.getDepartmentId() != null) {
            Department dept = departmentRepo.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));
            course.setDepartment(dept);
        }

        return toDto(save(course));
    }
    public CourseResponseDto updateDto(Long id, CourseRequestDto dto) {
        Course course = findById(id);
        if (course == null) throw new RuntimeException("Course not found");

        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());
        course.setCredits(dto.getCredits());

        if (dto.getDepartmentId() != null) {
            Department dept = departmentRepo.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));
            course.setDepartment(dept);
        }
        return toDto(save(course));
    }


    public CourseResponseDto toDto(Course c) {

        return new CourseResponseDto(
                c.getId(),
                c.getTitle(),
                c.getDescription(),
                c.getCredits(),
                c.getDepartment().getName()
        );
    }

}

