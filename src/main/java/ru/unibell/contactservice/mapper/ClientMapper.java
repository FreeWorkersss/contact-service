package ru.unibell.contactservice.mapper;

import org.mapstruct.Mapper;
import ru.unibell.contactservice.dto.request.ClientRequestDto;
import ru.unibell.contactservice.dto.request.EmailRequestDto;
import ru.unibell.contactservice.dto.request.PhoneRequestDto;
import ru.unibell.contactservice.dto.response.ClientContactsResponseDto;
import ru.unibell.contactservice.dto.response.ClientResponseDto;
import ru.unibell.contactservice.dto.response.EmailResponseDto;
import ru.unibell.contactservice.dto.response.PhoneResponseDto;
import ru.unibell.contactservice.model.Client;
import ru.unibell.contactservice.model.Email;
import ru.unibell.contactservice.model.Phone;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client toEntity(ClientRequestDto clientRequestDto);

    ClientResponseDto toClientResponseDto(Client client);

    ClientContactsResponseDto toClientContactsResponseDto(Client client);

    Phone toEntity(PhoneRequestDto phoneRequestDto);

    PhoneResponseDto toPhoneResponseDto(Phone phone);

    Email toEntity(EmailRequestDto emailRequestDto);

    EmailResponseDto toEmailResponseDto(Email email);

    List<PhoneResponseDto> toPhoneResponseDtoList(List<Phone> phones);

    List<EmailResponseDto> toEmailResponseDtoList(List<Email> emails);
}
