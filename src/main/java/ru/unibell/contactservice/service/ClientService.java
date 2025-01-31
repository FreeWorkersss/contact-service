package ru.unibell.contactservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.unibell.contactservice.dto.request.ClientRequestDto;
import ru.unibell.contactservice.dto.request.EmailRequestDto;
import ru.unibell.contactservice.dto.request.PhoneRequestDto;
import ru.unibell.contactservice.dto.response.ClientContactsResponseDto;
import ru.unibell.contactservice.dto.response.ClientResponseDto;
import ru.unibell.contactservice.dto.response.EmailResponseDto;
import ru.unibell.contactservice.dto.response.PhoneResponseDto;

import java.util.List;

public interface ClientService {

    ClientResponseDto addClient(ClientRequestDto requestDto);

    PhoneResponseDto addPhone(Long clientId, PhoneRequestDto phoneRequestDto);

    EmailResponseDto addEmail(Long clientId, EmailRequestDto emailRequestDto);

    Page<ClientResponseDto> getClients(Pageable pageable);

    ClientResponseDto getClientById(Long id);

    ClientContactsResponseDto getClientContacts(Long clientId, Pageable pageable);

    List<?> getClientContactsByType(Long clientId, String type, Pageable pageable);
}
