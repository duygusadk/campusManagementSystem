package com.example.campusManagement.repo;

import com.example.campusManagement.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student,Long> {
}
