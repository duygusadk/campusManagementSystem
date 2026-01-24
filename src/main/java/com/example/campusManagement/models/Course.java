package com.example.campusManagement.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
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
public class Course extends BaseEntity{
    private String title;
    private String description;
    private int credits;

    @OneToMany(mappedBy = " course",fetch = FetchType.LAZY)
    private List<Enrollment> enrollments=new ArrayList<>();

    @ManyToOne
    private Department department;
    public Course(String title, String description, int credits) {
        this.title = title;
        this.description = description;
        this.credits = credits;
    }
}
