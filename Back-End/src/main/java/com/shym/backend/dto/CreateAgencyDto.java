package com.shym.backend.dto;

public record CreateAgencyDto (
        String name,
        String description,
        String phone,
        String email,
        String password
) {}
