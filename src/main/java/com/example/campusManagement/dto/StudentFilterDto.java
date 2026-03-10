package com.example.campusManagement.dto;

import lombok.Setter;

@Setter
public class StudentFilterDto {
    private String studentName;
    private String city;
    private String clubName;
    private String courseName;
    private int enrollmentYear;

    public String getStudentName() {
        return studentName;
    }

    public String getCity() {
        return city;
    }

    public String getClubName() {
        return clubName;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getEnrollmentYear() {
        return enrollmentYear;
    }
}
