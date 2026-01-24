package com.example.campusManagement.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Department extends BaseEntity{
    private String name;
    private String headOfDepartment;

    @OneToMany(mappedBy = "department",fetch = FetchType.LAZY)
    private List<Course> courses=new ArrayList<>();

    @OneToMany(mappedBy = "department",fetch = FetchType.LAZY)
    private List<Professor>professors=new ArrayList<>();
    public Department(String name, String headOfDepartment) {
        this.name = name;
        this.headOfDepartment = headOfDepartment;
    }
}
