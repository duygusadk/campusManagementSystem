package com.example.campusManagement.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "Student")
public class Student extends BaseEntity{
    private String firstName;
    private String lastName;
    private String email;
    private String facultyNumber;

    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL,orphanRemoval = true)
    private Address address;
    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Enrollment>enrollments=new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "student_club",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "club_id"))
    private List<Club>clubs=new ArrayList<>();

    public Student(String firstName, String lastName, String email, String facultyNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.facultyNumber = facultyNumber;

    }
}
