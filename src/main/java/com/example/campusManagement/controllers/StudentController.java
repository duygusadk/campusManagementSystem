package com.example.campusManagement.controllers;

import com.example.campusManagement.dto.StudentFilterDto;
import com.example.campusManagement.dto.request.StudentRequestDto;
import com.example.campusManagement.dto.response.StudentResponseDto;
import com.example.campusManagement.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<StudentResponseDto> getAll() {
        return studentService.findAllDto();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> getById(@PathVariable Long id) {
        StudentResponseDto s = studentService.findByIdDto(id);
        if (s != null) {
            return ResponseEntity.ok(s);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<StudentResponseDto> create(@RequestBody StudentRequestDto student) {

        StudentResponseDto s = studentService.create(student);

        if (s != null) {
            return ResponseEntity.ok(s);
        }

        return ResponseEntity.badRequest().build();
    }
    @PostMapping("/search")
    public ResponseEntity<List<StudentResponseDto>> searchStudents(@RequestBody StudentFilterDto student){
        List<StudentResponseDto>students= studentService.findStudents(student);
        if (!students.isEmpty() ) {
            return ResponseEntity.ok(students);
        }

        return ResponseEntity.ok(null);
    }
    @PostMapping("/{studentId}/clubs/{clubId}")
    public ResponseEntity<StudentResponseDto>addToClub(@PathVariable Long studentId,@PathVariable Long clubId){
        studentService.addStudentToClub(studentId,clubId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDto> update(@RequestBody StudentRequestDto student, @PathVariable Long id) {

        return ResponseEntity.ok(studentService.updateDto(id,student));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

