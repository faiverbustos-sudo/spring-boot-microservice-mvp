package com.microservice.person.infrastructure.adapters.input.rest;

import com.microservice.person.domain.model.Person;
import com.microservice.person.domain.ports.in.CreatePersonUseCase;
import com.microservice.person.infrastructure.adapters.input.rest.dto.PersonRequest;
import com.microservice.person.infrastructure.adapters.input.rest.dto.PersonResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {

    private final CreatePersonUseCase createPersonUseCase;

    public PersonController(CreatePersonUseCase createPersonUseCase) {
        this.createPersonUseCase = createPersonUseCase;
    }

    @PostMapping
    public ResponseEntity<PersonResponse> createPerson(@Valid @RequestBody PersonRequest request) {
        Person personDomain = Person.builder()
                .name(request.getName())
                .lastName(request.getLastName())
                .phone(request.getPhone())
                .email(request.getEmail())
                .build();

        Person created = createPersonUseCase.createPerson(personDomain);

        PersonResponse response = PersonResponse.builder()
                .id(created.getId())
                .name(created.getName())
                .lastName(created.getLastName())
                .phone(created.getPhone())
                .email(created.getEmail())
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
