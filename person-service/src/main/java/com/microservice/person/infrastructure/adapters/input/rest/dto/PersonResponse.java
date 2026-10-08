package com.microservice.person.infrastructure.adapters.input.rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonResponse {
    private Long id;
    private String name;
    private String lastName;
    private String phone;
    private String email;
}
