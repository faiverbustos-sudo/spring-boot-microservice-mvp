package com.microservice.person.infrastructure.adapters.output.persistence;

import com.microservice.person.domain.model.Person;
import com.microservice.person.domain.ports.out.PersonRepositoryPort;
import com.microservice.person.infrastructure.adapters.output.persistence.entity.PersonEntity;
import com.microservice.person.infrastructure.adapters.output.persistence.mapper.PersonEntityMapper;
import com.microservice.person.infrastructure.adapters.output.persistence.repository.SpringDataPersonRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PersonPersistenceAdapter implements PersonRepositoryPort {

    private final SpringDataPersonRepository repository;

    public PersonPersistenceAdapter(SpringDataPersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public Person save(Person person) {
        PersonEntity entity = PersonEntityMapper.toEntity(person);
        PersonEntity savedEntity = repository.save(entity);
        return PersonEntityMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Person> findById(Long id) {
        return repository.findById(id)
                .map(PersonEntityMapper::toDomain);
    }

    @Override
    public Optional<Person> findByEmail(String email) {
        return repository.findByEmail(email)
                .map(PersonEntityMapper::toDomain);
    }

    @Override
    public List<Person> findAll() {
        return repository.findAll().stream()
                .map(PersonEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
