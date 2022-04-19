package com.shym.backend.dto;

import org.springframework.web.multipart.MultipartFile;

public record CreateAgencyDto (
        String name,
        String description,
        String phone,
        String email,
        String password
) {}
