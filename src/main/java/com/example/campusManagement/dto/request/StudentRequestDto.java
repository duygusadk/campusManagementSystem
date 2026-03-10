package com.example.campusManagement.dto.request;

import lombok.Data;

@Data
public class StudentRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String facultyNumber;
    private String street;
    private String city;
    private String country;
}
