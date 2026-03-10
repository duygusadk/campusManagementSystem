package com.example.campusManagement.dto.request;

import lombok.Data;

@Data
public class EnrollmentRequestDto {
    private int enrollmentYear;
    private Long studentId;
    private Long courseId;
}
