package com.microservice.person.infrastructure.adapters.output.persistence;

import com.microservice.person.domain.model.Person;
import com.microservice.person.domain.ports.out.PersonRepositoryPort;
import com.microservice.person.infrastructure.adapters.output.persistence.entity.PersonEntity;
import com.microservice.person.infrastructure.adapters.output.persistence.mapper.PersonEntityMapper;
import com.microservice.person.infrastructure.adapters.output.persistence.repository.SpringDataPersonRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PersonPersistenceAdapter implements PersonRepositoryPort {

    private final SpringDataPersonRepository repository;

    public PersonPersistenceAdapter(SpringDataPersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public Person save(Person person) {
        PersonEntity entity = PersonEntityMapper.toEntity(person);
        PersonEntity saved = repository.save(entity);
        return PersonEntityMapper.toDomain(saved);
    }

    @Override
    public Optional<Person> findByEmail(String email) {
        return repository.findByEmail(email)
                .map(PersonEntityMapper::toDomain);
    }
}
