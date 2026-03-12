package com.example.campusManagement.controllers;

import com.example.campusManagement.dto.request.ProfessorRequestDto;
import com.example.campusManagement.dto.response.ProfessorResponseDto;
import com.example.campusManagement.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professors")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public List<ProfessorResponseDto> getAll() {
        return professorService.findAllDto();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorResponseDto> getById(@PathVariable Long id){
        ProfessorResponseDto p=professorService.findByIdDto(id);
        if(p!=null){
            return ResponseEntity.ok(p);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ProfessorResponseDto>create(@RequestBody ProfessorRequestDto professor){

        ProfessorResponseDto p=professorService.create(professor);

        if(p!=null){
            return ResponseEntity.ok(p);
        }

        return ResponseEntity.badRequest().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProfessorResponseDto> update(@RequestBody ProfessorRequestDto professor, @PathVariable Long id) {

        return ResponseEntity.ok(professorService.updateDto(id,professor));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteById(@PathVariable Long id){
        professorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
