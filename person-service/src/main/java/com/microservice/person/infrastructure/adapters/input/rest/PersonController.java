package com.microservice.person.infrastructure.adapters.input.rest;

import com.microservice.person.domain.model.Person;
import com.microservice.person.domain.ports.in.PersonUseCase;
import com.microservice.person.infrastructure.adapters.input.rest.dto.PersonRequest;
import com.microservice.person.infrastructure.adapters.input.rest.dto.PersonResponse;
import com.microservice.person.infrastructure.adapters.input.rest.mapper.PersonRestMapper;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/persons")
@SecurityRequirement(name = "bearerAuth") // Configura Swagger para enviar el token JWT en esta API
public class PersonController {

    private final PersonUseCase personUseCase;

    public PersonController(PersonUseCase personUseCase) {
        this.personUseCase = personUseCase;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<List<PersonResponse>> getAll() {
        List<PersonResponse> responses = personUseCase.findAll().stream()
                .map(PersonRestMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<PersonResponse> getById(@PathVariable Long id) {
        return personUseCase.findById(id)
                .map(person -> ResponseEntity.ok(PersonRestMapper.toResponse(person)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PersonResponse> create(@Valid @RequestBody PersonRequest request) {
        Person domainPerson = PersonRestMapper.toDomain(request);
        Person createdPerson = personUseCase.create(domainPerson);
        return new ResponseEntity<>(PersonRestMapper.toResponse(createdPerson), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PersonResponse> update(@PathVariable Long id, @Valid @RequestBody PersonRequest request) {
        Person domainPerson = PersonRestMapper.toDomain(request);
        try {
            Person updatedPerson = personUseCase.update(id, domainPerson);
            return ResponseEntity.ok(PersonRestMapper.toResponse(updatedPerson));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            personUseCase.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
