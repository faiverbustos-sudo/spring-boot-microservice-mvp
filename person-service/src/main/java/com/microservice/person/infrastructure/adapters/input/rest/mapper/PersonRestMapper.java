package com.microservice.person.infrastructure.adapters.input.rest.mapper;

import com.microservice.person.domain.model.Person;
import com.microservice.person.infrastructure.adapters.input.rest.dto.PersonRequest;
import com.microservice.person.infrastructure.adapters.input.rest.dto.PersonResponse;

public class PersonRestMapper {

    public static Person toDomain(PersonRequest request) {
        if (request == null) {
            return null;
        }
        return Person.builder()
                .name(request.getName())
                .lastName(request.getLastName())
                .phone(request.getPhone())
                .email(request.getEmail())
                .build();
    }

    public static PersonResponse toResponse(Person person) {
        if (person == null) {
            return null;
        }
        return PersonResponse.builder()
                .id(person.getId())
                .name(person.getName())
                .lastName(person.getLastName())
                .phone(person.getPhone())
                .email(person.getEmail())
                .build();
    }
}
