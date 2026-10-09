package com.microservice.person.domain.ports.in;

import com.microservice.person.domain.model.Person;
import java.util.List;
import java.util.Optional;

public interface PersonUseCase {
    Person create(Person person);
    List<Person> findAll();
    Optional<Person> findById(Long id);
    Person update(Long id, Person person);
    void deleteById(Long id);
}
