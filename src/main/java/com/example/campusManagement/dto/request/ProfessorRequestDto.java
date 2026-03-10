package com.example.campusManagement.dto.request;

import lombok.Data;

@Data
public class ProfessorRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String academicRank;
    private Long departmentId;
}
