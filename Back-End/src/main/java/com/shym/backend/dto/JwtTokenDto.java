package com.shym.backend.dto;

import com.shym.backend.enumeration.Role;

public record JwtTokenDto(String token, boolean firstTime, Role role) {}
