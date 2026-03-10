package com.example.campusManagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String academicRank;
    private String departmentName;
}
