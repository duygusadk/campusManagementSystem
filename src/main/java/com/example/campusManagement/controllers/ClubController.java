package com.example.campusManagement.controllers;

import com.example.campusManagement.dto.request.ClubRequestDto;
import com.example.campusManagement.dto.response.ClubResponseDto;
import com.example.campusManagement.services.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clubs")
public class ClubController {

    @Autowired
    private ClubService clubService;
    @GetMapping
    public List<ClubResponseDto> getAll() {
        return clubService.findAllDto();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClubResponseDto> getById(@PathVariable Long id){
        ClubResponseDto c=clubService.findByIdDto(id);
        if(c!=null){
            return ResponseEntity.ok(c);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ClubResponseDto>create(@RequestBody ClubRequestDto club){

        ClubResponseDto c=clubService.create(club);

        if(c!=null){
            return ResponseEntity.ok(c);
        }

        return ResponseEntity.badRequest().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<ClubResponseDto> update(@RequestBody ClubRequestDto club, @PathVariable Long id) {

        return ResponseEntity.ok(clubService.updateDto(id,club));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteById(@PathVariable Long id){
        clubService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
