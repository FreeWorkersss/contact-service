package ru.unibell.contactservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailResponseDto {
    private Long id;
    private String address;
}
