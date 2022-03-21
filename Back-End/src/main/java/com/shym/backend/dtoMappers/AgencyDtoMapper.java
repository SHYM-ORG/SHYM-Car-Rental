package com.shym.backend.dtoMappers;

import com.shym.backend.dto.CreateAgencyDto;
import com.shym.backend.enumeration.Role;
import com.shym.backend.model.Agency;
import com.shym.backend.security.SecurityConfiguration;

public class AgencyDtoMapper {
    public static Agency createAgencyDtoMapper(CreateAgencyDto dto) {
        Agency agency = new Agency();
        agency.setName(dto.name());
        agency.setPhone(dto.phone());
        agency.setDescription(dto.description());
        agency.setEmail(dto.email());
        agency.setPassword(SecurityConfiguration.passwordEncoder().encode(dto.password()));
        agency.setLocation(null);
        agency.setFirstTime(true);
        agency.setRole(Role.AGENCY);
        return agency;
    }
}
