package ru.unibell.contactservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ClientContactsResponseDto {
    private Long id;
    private String name;
    private List<PhoneResponseDto> phones;
    private List<EmailResponseDto> emails;
}
