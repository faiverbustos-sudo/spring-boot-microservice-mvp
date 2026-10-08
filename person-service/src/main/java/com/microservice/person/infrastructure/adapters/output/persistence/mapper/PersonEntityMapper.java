package com.microservice.person.infrastructure.adapters.output.persistence.mapper;

import com.microservice.person.domain.model.Person;
import com.microservice.person.infrastructure.adapters.output.persistence.entity.PersonEntity;

public class PersonEntityMapper {

    public static PersonEntity toEntity(Person person) {
        if (person == null) return null;
        return new PersonEntity(
                person.getId(),
                person.getName(),
                person.getLastName(),
                person.getPhone(),
                person.getEmail()
        );
    }

    public static Person toDomain(PersonEntity entity) {
        if (entity == null) return null;
        return Person.builder()
                .id(entity.getId())
                .name(entity.getName())
                .lastName(entity.getLastName())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .build();
    }
}
