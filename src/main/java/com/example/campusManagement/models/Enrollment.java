package com.example.campusManagement.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Enrollment extends BaseEntity{
    private int enrollmentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    public Enrollment(int enrollmentDate, Course course, Student student) {
        this.enrollmentDate = enrollmentDate;
        this.course = course;
        this.student = student;
    }

    public void setEnrollmentYear(int enrollmentYear) {
    }

    public int getEnrollmentYear() {
    }
}
