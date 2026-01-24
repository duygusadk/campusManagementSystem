package com.example.campusManagement.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Professor extends BaseEntity{
    private String firstName;
    private String lastName;
    private String email;
    private String academicRank;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    public Professor(String firstName, String lastName, String email, String academicRank) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.academicRank = academicRank;
    }
}
