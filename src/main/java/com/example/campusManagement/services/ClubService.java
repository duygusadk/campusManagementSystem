package com.example.campusManagement.services;

import com.example.campusManagement.dto.request.ClubRequestDto;
import com.example.campusManagement.dto.response.ClubResponseDto;
import com.example.campusManagement.models.Club;
import com.example.campusManagement.repo.ClubRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClubService extends BaseService<Club, ClubRepo> {
    public ClubService(ClubRepo repository) {
        super(repository);
    }

    public List<ClubResponseDto> findAllDto() {
        return findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public ClubResponseDto create(ClubRequestDto dto) {
        Club club = new Club();
        club.setName(dto.getName());
        club.setDescription(dto.getDescription());

        return toDto(save(club));
    }

    public ClubResponseDto findByIdDto(Long id) {
        return toDto(findById(id));
    }

    public ClubResponseDto updateDto(Long id, ClubRequestDto dto) {
        Club club = findById(id);
        if (club == null) throw new RuntimeException("Club not found");

        club.setName(dto.getName());
        club.setDescription(dto.getDescription());

        return toDto(save(club));
    }

    public ClubResponseDto toDto(Club c) {
        return new ClubResponseDto(
                c.getId(),
                c.getName(),
                c.getDescription()
        );
    }
}
