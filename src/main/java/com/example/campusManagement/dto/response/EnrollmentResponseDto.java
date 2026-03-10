package com.example.campusManagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentResponseDto {
    private Long id;
    private int enrollmentYear;
    private String studentFullName;
    private String courseTitle;
}
