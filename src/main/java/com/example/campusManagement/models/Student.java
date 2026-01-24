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

    @OneToOne(fetch = FetchType.LAZY)
    private Address address;
    @OneToMany(mappedBy = "enrollment",fetch = FetchType.LAZY)
    private List<Enrollment> enrollments=new ArrayList<>();
}
