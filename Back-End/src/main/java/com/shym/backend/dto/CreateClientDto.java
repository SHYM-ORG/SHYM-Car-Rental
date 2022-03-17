package com.shym.backend.dto;

public record CreateClientDto (
        String firstName,
        String lastName,
        String email,
        String password
) {}
