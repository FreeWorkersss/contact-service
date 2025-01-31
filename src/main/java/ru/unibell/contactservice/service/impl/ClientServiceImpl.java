package ru.unibell.contactservice.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.unibell.contactservice.dto.request.ClientRequestDto;
import ru.unibell.contactservice.dto.request.EmailRequestDto;
import ru.unibell.contactservice.dto.request.PhoneRequestDto;
import ru.unibell.contactservice.dto.response.ClientContactsResponseDto;
import ru.unibell.contactservice.dto.response.ClientResponseDto;
import ru.unibell.contactservice.dto.response.EmailResponseDto;
import ru.unibell.contactservice.dto.response.PhoneResponseDto;
import ru.unibell.contactservice.exception.InvalidContactTypeException;
import ru.unibell.contactservice.exception.InvalidEmailException;
import ru.unibell.contactservice.mapper.ClientMapper;
import ru.unibell.contactservice.model.Client;
import ru.unibell.contactservice.model.Email;
import ru.unibell.contactservice.model.Phone;
import ru.unibell.contactservice.repository.ClientRepository;
import ru.unibell.contactservice.repository.EmailRepository;
import ru.unibell.contactservice.repository.PhoneRepository;
import ru.unibell.contactservice.service.ClientService;

import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final PhoneRepository phoneRepository;
    private final EmailRepository emailRepository;
    private final ClientMapper clientMapper;
    private static final String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    @Override
    public ClientResponseDto addClient(ClientRequestDto requestDto) {
        Client client = clientMapper.toEntity(requestDto);
        client = clientRepository.save(client);
        return clientMapper.toClientResponseDto(client);
    }

    @Override
    public PhoneResponseDto addPhone(Long clientId, PhoneRequestDto phoneRequestDto) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with id: " + clientId));

        Phone phone = clientMapper.toEntity(phoneRequestDto);
        phone.setClient(client);
        phone = phoneRepository.save(phone);

        return clientMapper.toPhoneResponseDto(phone);
    }

    @Override
    public EmailResponseDto addEmail(Long clientId, EmailRequestDto emailRequestDto) {
        if (!isValidEmail(emailRequestDto.getAddress())) {
            throw new InvalidEmailException("Invalid email format: " + emailRequestDto.getAddress());
        }
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with id: " + clientId));

        Email email = clientMapper.toEntity(emailRequestDto);
        email.setClient(client);
        email = emailRepository.save(email);

        return clientMapper.toEmailResponseDto(email);
    }

    @Override
    public Page<ClientResponseDto> getClients(Pageable pageable) {
        return clientRepository.findAll(pageable)
                .map(clientMapper::toClientResponseDto);
    }

    @Override
    public ClientResponseDto getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with id: " + id));
        return clientMapper.toClientResponseDto(client);
    }

    @Override
    public ClientContactsResponseDto getClientContacts(Long clientId, Pageable pageable) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with id: " + clientId));

        return clientMapper.toClientContactsResponseDto(client);
    }

    @Override
    public List<?> getClientContactsByType(Long clientId, String type, Pageable pageable) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with id: " + clientId));

        if ("phone".equalsIgnoreCase(type)) {
            return clientMapper.toPhoneResponseDtoList(client.getPhones());
        } else if ("email".equalsIgnoreCase(type)) {
            return clientMapper.toEmailResponseDtoList(client.getEmails());
        } else {
            throw new InvalidContactTypeException("Invalid contact type: " + type);
        }
    }

    private boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
}