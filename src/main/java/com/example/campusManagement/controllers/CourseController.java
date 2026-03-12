package com.example.campusManagement.controllers;

import com.example.campusManagement.dto.request.CourseRequestDto;
import com.example.campusManagement.dto.response.CourseResponseDto;
import com.example.campusManagement.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @GetMapping
    public List<CourseResponseDto> getAll() {
        return courseService.findAllDto();
    }
    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDto> getById(@PathVariable Long id){
        CourseResponseDto c=courseService.findByIdDto(id);
        if(c!=null){
            return ResponseEntity.ok(c);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CourseResponseDto>create(@RequestBody CourseRequestDto course){

        CourseResponseDto c=courseService.create(course);

        if(c!=null){
            return ResponseEntity.ok(c);
        }

        return ResponseEntity.badRequest().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDto> update(@RequestBody CourseRequestDto course, @PathVariable Long id) {

        return ResponseEntity.ok(courseService.updateDto(id,course));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteById(@PathVariable Long id){
        courseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
