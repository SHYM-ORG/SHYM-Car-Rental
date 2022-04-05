package com.shym.backend.service;

import com.shym.backend.dto.ClientPreferencesDTO;
import com.shym.backend.dto.CreateClientDto;
import com.shym.backend.dtoMappers.ClientDtoMapper;
import com.shym.backend.exception.UserAlreadyExistsException;
import com.shym.backend.model.Client;
import com.shym.backend.repository.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client createClient(CreateClientDto dto) {
        if(clientRepository.existsByEmail(dto.email())) throw new UserAlreadyExistsException("Client exits!");
        Client client = ClientDtoMapper.createClientDtoMapper(dto);
        return clientRepository.save(client);
    }

    public Client addPreferences(ClientPreferencesDTO dto, String email) {
        Client client = clientRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user not found!"));
        client.setFaveModels(dto.modelPreferences());
        client.setFaveCategories(dto.catPreferences());
        clientRepository.save(client);
        return client;
    }
}
