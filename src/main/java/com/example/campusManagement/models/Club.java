package com.example.campusManagement.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Club extends BaseEntity{
    private String name;
    private String description;

    @ManyToMany(mappedBy = "club",fetch = FetchType.LAZY)
    private List<Student> students=new ArrayList<>();
    public Club(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
