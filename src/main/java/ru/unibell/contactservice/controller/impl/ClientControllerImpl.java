package ru.unibell.contactservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.unibell.contactservice.controller.ClientController;
import ru.unibell.contactservice.dto.request.ClientRequestDto;
import ru.unibell.contactservice.dto.request.EmailRequestDto;
import ru.unibell.contactservice.dto.request.PhoneRequestDto;
import ru.unibell.contactservice.dto.response.ClientContactsResponseDto;
import ru.unibell.contactservice.dto.response.ClientResponseDto;
import ru.unibell.contactservice.dto.response.EmailResponseDto;
import ru.unibell.contactservice.dto.response.PhoneResponseDto;
import ru.unibell.contactservice.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
public class ClientControllerImpl implements ClientController {

    private final ClientService clientService;

    @Override
    @PostMapping
    public ResponseEntity<ClientResponseDto> addClient(@RequestBody ClientRequestDto requestDto) {
        ClientResponseDto responseDto = clientService.addClient(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @Override
    @PostMapping("/{clientId}/phones")
    public ResponseEntity<PhoneResponseDto> addPhone(
            @PathVariable Long clientId,
            @RequestBody PhoneRequestDto phoneRequestDto) {
        PhoneResponseDto responseDto = clientService.addPhone(clientId, phoneRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @Override
    @PostMapping("/{clientId}/emails")
    public ResponseEntity<EmailResponseDto> addEmail(
            @PathVariable Long clientId,
            @RequestBody EmailRequestDto emailRequestDto) {
        EmailResponseDto responseDto = clientService.addEmail(clientId, emailRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<ClientResponseDto>> getClients(Pageable pageable) {
        Page<ClientResponseDto> clients = clientService.getClients(pageable);
        return ResponseEntity.ok(clients);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDto> getClientById(@PathVariable Long id) {
        ClientResponseDto responseDto = clientService.getClientById(id);
        return ResponseEntity.ok(responseDto);
    }

    @Override
    @GetMapping("/{clientId}/contacts")
    public ResponseEntity<ClientContactsResponseDto> getClientContacts(
            @PathVariable Long clientId,
            Pageable pageable) {
        ClientContactsResponseDto responseDto = clientService.getClientContacts(clientId, pageable);
        return ResponseEntity.ok(responseDto);
    }

    @Override
    @GetMapping("/{clientId}/contacts/{type}")
    public ResponseEntity<List<?>> getClientContactsByType(
            @PathVariable Long clientId,
            @PathVariable String type,
            Pageable pageable) {
        List<?> contacts = clientService.getClientContactsByType(clientId, type, pageable);
        return ResponseEntity.ok(contacts);
    }
}
