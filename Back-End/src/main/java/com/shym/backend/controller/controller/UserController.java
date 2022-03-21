package com.shym.backend.controller.controller;

import com.shym.backend.dto.CreateAgencyDto;
import com.shym.backend.dto.CreateClientDto;
import com.shym.backend.model.Agency;
import com.shym.backend.model.Client;
import com.shym.backend.service.AgencyService;
import com.shym.backend.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
public class UserController {

    ClientService clientService;
    AgencyService agencyService;

    public UserController(ClientService clientService, AgencyService agencyService) {
        this.clientService = clientService;
        this.agencyService = agencyService;
    }

    @PostMapping("/create/client")
    public ResponseEntity<Client> createClient(@RequestBody CreateClientDto createClientDto) {
        return new ResponseEntity<>(
                clientService.createClient(createClientDto),
                HttpStatus.OK
        );
    }

    @PostMapping("/create/agency")
    public ResponseEntity<Agency> createAgency(@RequestBody CreateAgencyDto createAgencyDto) {
        return new ResponseEntity<>(
                agencyService.createAgency(createAgencyDto),
                HttpStatus.OK
        );
    }
}
