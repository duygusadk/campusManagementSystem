package com.example.campusManagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClubResponseDto {
    private Long id;
    private String name;
    private String description;
}
