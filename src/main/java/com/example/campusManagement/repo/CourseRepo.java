package com.example.campusManagement.repo;

import com.example.campusManagement.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course,Long> {
}
