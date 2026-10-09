package com.microservice.person.application.service;

import com.microservice.person.domain.model.Person;
import com.microservice.person.domain.ports.in.PersonUseCase;
import com.microservice.person.domain.ports.out.PersonRepositoryPort;

import java.util.List;
import java.util.Optional;

public class PersonService implements PersonUseCase {

    private final PersonRepositoryPort personRepositoryPort;

    public PersonService(PersonRepositoryPort personRepositoryPort) {
        this.personRepositoryPort = personRepositoryPort;
    }

    @Override
    public Person create(Person person) {
        personRepositoryPort.findByEmail(person.getEmail()).ifPresent(p -> {
            throw new IllegalArgumentException("El email ya se encuentra registrado.");
        });
        return personRepositoryPort.save(person);
    }

    @Override
    public List<Person> findAll() {
        return personRepositoryPort.findAll();
    }

    @Override
    public Optional<Person> findById(Long id) {
        return personRepositoryPort.findById(id);
    }

    @Override
    public Person update(Long id, Person person) {
        Person existingPerson = personRepositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada con el ID: " + id));

        // Validar que el nuevo email no pertenezca a otra persona
        personRepositoryPort.findByEmail(person.getEmail()).ifPresent(p -> {
            if (!p.getId().equals(id)) {
                throw new IllegalArgumentException("El email ya está en uso por otro registro.");
            }
        });

        // Actualizar los atributos usando los nombres en inglés
        existingPerson.setName(person.getName());
        existingPerson.setLastName(person.getLastName());
        existingPerson.setPhone(person.getPhone());
        existingPerson.setEmail(person.getEmail());

        return personRepositoryPort.save(existingPerson);
    }

    @Override
    public void deleteById(Long id) {
        Person existingPerson = personRepositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada con el ID: " + id));

        personRepositoryPort.deleteById(existingPerson.getId());
    }
}
