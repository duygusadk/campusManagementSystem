package com.example.campusManagement.controllers;

import com.example.campusManagement.dto.request.EnrollmentRequestDto;
import com.example.campusManagement.dto.response.EnrollmentResponseDto;
import com.example.campusManagement.services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping
    public List<EnrollmentResponseDto> getAll() {
        return enrollmentService.findAllDto();
    }
    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentResponseDto> getById(@PathVariable Long id){
        EnrollmentResponseDto e=enrollmentService.findByIdDto(id);
        if(e!=null){
            return ResponseEntity.ok(e);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<EnrollmentResponseDto>create(@RequestBody EnrollmentRequestDto enrollment){

        EnrollmentResponseDto e=enrollmentService.addStudentToCourse(enrollment);

        if(e!=null){
            return ResponseEntity.ok(e);
        }

        return ResponseEntity.badRequest().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<EnrollmentResponseDto> update(@RequestBody EnrollmentRequestDto enrollment, @PathVariable Long id) {

        return ResponseEntity.ok(enrollmentService.updateDto(id,enrollment));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteById(@PathVariable Long id){
        enrollmentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
