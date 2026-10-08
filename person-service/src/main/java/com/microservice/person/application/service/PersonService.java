package com.microservice.person.application.service;

import com.microservice.person.domain.model.Person;
import com.microservice.person.domain.ports.in.CreatePersonUseCase;
import com.microservice.person.domain.ports.out.PersonRepositoryPort;

public class PersonService implements CreatePersonUseCase {

    private final PersonRepositoryPort personRepositoryPort;

    public PersonService(PersonRepositoryPort personRepositoryPort) {
        this.personRepositoryPort = personRepositoryPort;
    }

    @Override
    public Person createPerson(Person person) {
        personRepositoryPort.findByEmail(person.getEmail()).ifPresent(p -> {
            throw new IllegalArgumentException("El email ya se encuentra registrado.");
        });
        return personRepositoryPort.save(person);
    }
}
