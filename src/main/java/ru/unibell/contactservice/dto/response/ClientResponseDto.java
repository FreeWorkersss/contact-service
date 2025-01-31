package ru.unibell.contactservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientResponseDto {
    private Long id;
    private String name;
}
