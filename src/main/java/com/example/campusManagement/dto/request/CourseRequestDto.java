package com.example.campusManagement.dto.request;

import lombok.Data;

@Data
public class CourseRequestDto {
    private String title;
    private String description;
    private int credits;
    private Long departmentId;
}
