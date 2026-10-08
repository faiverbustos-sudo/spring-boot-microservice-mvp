package com.microservice.person.infrastructure.config;

import com.microservice.person.application.service.PersonService;
import com.microservice.person.domain.ports.in.CreatePersonUseCase;
import com.microservice.person.domain.ports.out.PersonRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CreatePersonUseCase createPersonUseCase(PersonRepositoryPort personRepositoryPort) {
        return new PersonService(personRepositoryPort);
    }
}
