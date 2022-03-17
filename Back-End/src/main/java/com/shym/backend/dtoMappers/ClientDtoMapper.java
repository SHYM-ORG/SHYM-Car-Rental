package com.shym.backend.dtoMappers;

import com.shym.backend.dto.CreateClientDto;
import com.shym.backend.enumeration.Role;
import com.shym.backend.model.Client;
import com.shym.backend.security.SecurityConfiguration;

import java.util.ArrayList;

public class ClientDtoMapper {

    public static Client createClientDtoMapper(CreateClientDto dto) {
        Client client = new Client();
        client.setFirstName(dto.firstName());
        client.setLastName(dto.lastName());
        client.setEmail(dto.email());
        client.setPassword(SecurityConfiguration.passwordEncoder().encode(dto.password()));
        client.setLastLocation(null);
        client.setFirstTime(true);
        client.setFaveCategories(new ArrayList<>());
        client.setFaveModels(new ArrayList<>());
        client.setRole(Role.CLIENT);
        return client;
    }
}
