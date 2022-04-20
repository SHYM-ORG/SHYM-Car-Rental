package com.shym.backend.service;

import com.shym.backend.dto.ClientPreferencesDTO;
import com.shym.backend.dto.CreateClientDto;
import com.shym.backend.dtoMappers.ClientDtoMapper;
import com.shym.backend.exception.UserAlreadyExistsException;
import com.shym.backend.model.Client;
import com.shym.backend.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Service
@AllArgsConstructor
public class ClientService {

    private ClientRepository clientRepository;
    private FileService fileService;

    public Client createClient(CreateClientDto dto) {
        if(clientRepository.existsByEmail(dto.email())) throw new UserAlreadyExistsException("Client exits!");
        Client client = ClientDtoMapper.createClientDtoMapper(dto);
        try {
            fileService.uploadUserImage(client, dto.image());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return clientRepository.save(client);
    }

    public Client addPreferences(ClientPreferencesDTO dto, String email) {
        Client client = clientRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user not found!"));
        client.setFaveModels(dto.modelPreferences());
        client.setFaveCategories(dto.catPreferences());
        client.setFirstTime(false);
        clientRepository.save(client);
        return client;
    }
}
