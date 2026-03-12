package com.example.campusManagement.controllers;

import com.example.campusManagement.dto.request.DepartmentRequestDto;
import com.example.campusManagement.dto.response.DepartmentResponseDto;
import com.example.campusManagement.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public List<DepartmentResponseDto> getAll() {
        return departmentService.findAllDto();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDto> getById(@PathVariable Long id){
        DepartmentResponseDto d=departmentService.findByIdDto(id);
        if(d!=null){
            return ResponseEntity.ok(d);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<DepartmentResponseDto>create(@RequestBody DepartmentRequestDto department){

        DepartmentResponseDto d=departmentService.create(department);

        if(d!=null){
            return ResponseEntity.ok(d);
        }

        return ResponseEntity.badRequest().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponseDto> update(@RequestBody DepartmentRequestDto professor, @PathVariable Long id) {

        return ResponseEntity.ok(departmentService.updateDto(id,professor));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteById(@PathVariable Long id){
        departmentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
