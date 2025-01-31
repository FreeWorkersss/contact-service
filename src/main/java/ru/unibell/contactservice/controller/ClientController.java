package ru.unibell.contactservice.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.unibell.contactservice.dto.request.ClientRequestDto;
import ru.unibell.contactservice.dto.request.EmailRequestDto;
import ru.unibell.contactservice.dto.request.PhoneRequestDto;
import ru.unibell.contactservice.dto.response.ClientContactsResponseDto;
import ru.unibell.contactservice.dto.response.ClientResponseDto;
import ru.unibell.contactservice.dto.response.EmailResponseDto;
import ru.unibell.contactservice.dto.response.PhoneResponseDto;

import java.util.List;

@RestController
@RequestMapping("api/v1/clients")
public interface ClientController {

    @PostMapping
    ResponseEntity<ClientResponseDto> addClient(@RequestBody ClientRequestDto requestDTO);

    @PostMapping("/{clientId}/phones")
    ResponseEntity<PhoneResponseDto> addPhone(@PathVariable Long clientId, @RequestBody PhoneRequestDto phoneRequestDto);

    @PostMapping("/{clientId}/emails")
    ResponseEntity<EmailResponseDto> addEmail(@PathVariable Long clientId, @RequestBody EmailRequestDto emailRequestDto);

    @GetMapping
    ResponseEntity<Page<ClientResponseDto>> getClients(Pageable pageable);

    @GetMapping("/{id}")
    ResponseEntity<ClientResponseDto> getClientById(@PathVariable Long id);

    @GetMapping("/{clientId}/contacts")
    ResponseEntity<ClientContactsResponseDto> getClientContacts(@PathVariable Long clientId, Pageable pageable);

    @GetMapping("/{clientId}/contacts/{type}")
    ResponseEntity<List<?>> getClientContactsByType(@PathVariable Long clientId, @PathVariable String type, Pageable pageable);
}
