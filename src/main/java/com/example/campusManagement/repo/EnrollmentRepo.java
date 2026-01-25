package com.example.campusManagement.repo;

import com.example.campusManagement.models.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepo extends JpaRepository<Enrollment,Long> {
}
