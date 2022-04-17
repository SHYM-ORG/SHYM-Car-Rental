package com.shym.backend.dto;

import org.springframework.web.multipart.MultipartFile;

public record AgencyImageDto(
        MultipartFile image
) {
}
