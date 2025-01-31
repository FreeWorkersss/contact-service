package ru.unibell.contactservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PhoneResponseDto {
    private Long id;
    private String number; 
}
