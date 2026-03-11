package com.example.campusManagement.services;

import com.example.campusManagement.dto.request.ProfessorRequestDto;
import com.example.campusManagement.dto.response.ProfessorResponseDto;
import com.example.campusManagement.models.Department;
import com.example.campusManagement.models.Professor;
import com.example.campusManagement.repo.DepartmentRepo;
import com.example.campusManagement.repo.ProfessorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessorService extends BaseService<Professor, ProfessorRepo> {

    @Autowired
    private DepartmentRepo departmentRepo;

    public ProfessorService(ProfessorRepo repository) {
        super(repository);
    }

    public List<ProfessorResponseDto> findAllDto() {
        return findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public ProfessorResponseDto create(ProfessorRequestDto dto) {

        Professor professor = new Professor(dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getAcademicRank());

        if (dto.getDepartmentId() != null) {
            Department dept = departmentRepo.findById(dto.getDepartmentId()).orElseThrow(() -> new RuntimeException("Department not found"));
            professor.setDepartment(dept);
        }

        return toDto(save(professor));
    }

    public ProfessorResponseDto findByIdDto(Long id) {
        return toDto(findById(id));
    }

    public ProfessorResponseDto updateDto(Long id, ProfessorRequestDto dto) {
        Professor professor = findById(id);
        if (professor == null) throw new RuntimeException("Professor not found");

        professor.setFirstName(dto.getFirstName());
        professor.setLastName(dto.getLastName());
        professor.setEmail(dto.getEmail());
        professor.setAcademicRank(dto.getAcademicRank());


        if (dto.getDepartmentId() != null) {
            Department dept = departmentRepo.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));
            professor.setDepartment(dept);
        }

        return toDto(save(professor));
    }

    private ProfessorResponseDto toDto(Professor p) {
        return new ProfessorResponseDto(
                p.getId(), p.getFirstName(), p.getLastName(),
                p.getEmail(), p.getAcademicRank(),
                p.getDepartment().getName()
        );
    }

}
