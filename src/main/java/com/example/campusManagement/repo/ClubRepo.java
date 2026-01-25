package com.example.campusManagement.repo;

import com.example.campusManagement.models.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepo extends JpaRepository<Club,Long> {
}
