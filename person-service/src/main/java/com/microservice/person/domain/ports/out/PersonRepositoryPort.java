package com.microservice.person.domain.ports.out;

import com.microservice.person.domain.model.Person;
import java.util.Optional;

public interface PersonRepositoryPort {
    Person save(Person person);
    Optional<Person> findByEmail(String email);
}
