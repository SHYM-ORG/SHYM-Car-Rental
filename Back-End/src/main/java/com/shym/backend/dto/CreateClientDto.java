package com.shym.backend.dto;

import org.springframework.web.multipart.MultipartFile;

public record CreateClientDto (
        String firstName,
        String lastName,
        String email,
        String password,
        MultipartFile image
) {}
