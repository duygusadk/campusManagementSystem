package com.example.campusManagement.repo;

import com.example.campusManagement.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepo extends JpaRepository<Professor,Long> {
}
