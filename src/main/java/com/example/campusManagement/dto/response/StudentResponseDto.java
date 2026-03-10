package com.example.campusManagement.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@NoArgsConstructor
@Getter
public class StudentResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String facultyNumber;
    private String city;
    private List<String> clubNames;

    public StudentResponseDto(Long id, String firstName, String lastName, String email, String facultyNumber, String city, List<String> clubNames) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.facultyNumber = facultyNumber;
        this.city = city;
        this.clubNames = clubNames;
    }
}
