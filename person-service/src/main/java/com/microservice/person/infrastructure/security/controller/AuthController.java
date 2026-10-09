package com.microservice.person.infrastructure.security.controller;

import com.microservice.person.infrastructure.security.dto.AuthRequest;
import com.microservice.person.infrastructure.security.dto.AuthResponse;
import com.microservice.person.infrastructure.security.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager,
                          UserDetailsService userDetailsService,
                          JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        // 1. Validar credenciales con Spring Security
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // 2. Si es exitoso, cargar los detalles del usuario (incluyendo roles)
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

        // 3. Generar el token JWT
        String jwtToken = jwtService.generateToken(userDetails);

        // 4. Retornar el token
        return ResponseEntity.ok(AuthResponse.builder()
                .token(jwtToken)
                .build());
    }
}


