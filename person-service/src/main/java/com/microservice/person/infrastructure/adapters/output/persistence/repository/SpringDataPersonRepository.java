package com.microservice.person.infrastructure.adapters.output.persistence.repository;

import com.microservice.person.infrastructure.adapters.output.persistence.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SpringDataPersonRepository extends JpaRepository<PersonEntity, Long> {
    Optional<PersonEntity> findByEmail(String email);
}