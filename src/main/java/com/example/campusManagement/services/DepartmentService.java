package com.example.campusManagement.services;

import com.example.campusManagement.dto.request.DepartmentRequestDto;
import com.example.campusManagement.dto.response.DepartmentResponseDto;
import com.example.campusManagement.models.Course;
import com.example.campusManagement.models.Department;
import com.example.campusManagement.repo.DepartmentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService extends BaseService<Department, DepartmentRepo> {
    public DepartmentService(DepartmentRepo repository) {
        super(repository);
    }

    public List<DepartmentResponseDto> findAllDto() {
        return findAll().stream().map(this::toDto).collect(Collectors.toList());
    }
    public DepartmentResponseDto findByIdDto(Long id) {
        return toDto(findById(id));
    }
    public DepartmentResponseDto create(DepartmentRequestDto dto) {

        Department dept = new Department();
        dept.setName(dto.getName());
        dept.setHeadOfDepartment(dto.getHeadOfDepartment());

        return toDto(save(dept));
    }
    public DepartmentResponseDto updateDto(Long id, DepartmentRequestDto dto) {
        Department dept = findById(id);
        if (dept == null) throw new RuntimeException("Department not found");

        dept.setName(dto.getName());
        dept.setHeadOfDepartment(dto.getHeadOfDepartment());

        return toDto(save(dept));
    }

    public DepartmentResponseDto toDto(Department d) {

        List<String>courseTitles=d.getCourses().stream().map(Course::getTitle).toList();
        List<String>professorName=d.getProfessors().stream().map(p->p.getFirstName()+" "+p.getLastName()).toList();
        return new DepartmentResponseDto(d.getId(),d.getName(),d.getHeadOfDepartment(),courseTitles,professorName);
    }

}