package com.example.campusManagement.repo;

import com.example.campusManagement.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address,Long> {
}
