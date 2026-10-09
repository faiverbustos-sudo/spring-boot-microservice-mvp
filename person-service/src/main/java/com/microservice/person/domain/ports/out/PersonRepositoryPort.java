package com.microservice.person.domain.ports.out;

import com.microservice.person.domain.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepositoryPort {
    Person save(Person person);
    Optional<Person> findById(Long id);
    Optional<Person> findByEmail(String email);
    List<Person> findAll();
    void deleteById(Long id);
}
