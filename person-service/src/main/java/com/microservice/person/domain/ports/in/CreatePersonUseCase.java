package com.microservice.person.domain.ports.in;

import com.microservice.person.domain.model.Person;

public interface CreatePersonUseCase {
    Person createPerson(Person person);
}
