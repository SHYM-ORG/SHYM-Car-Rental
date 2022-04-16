package com.shym.backend.controller.controller;

import com.shym.backend.dto.ClientPreferencesDTO;
import com.shym.backend.dto.CreateAgencyDto;
import com.shym.backend.dto.CreateClientDto;
import com.shym.backend.dto.JwtLoginDto;
import com.shym.backend.model.Agency;
import com.shym.backend.model.Client;
import com.shym.backend.model.User;
import com.shym.backend.service.AgencyService;
import com.shym.backend.service.ClientService;
import com.shym.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class UserController {

    ClientService clientService;
    AgencyService agencyService;

    UserService userService;

    public UserController(ClientService clientService, AgencyService agencyService, UserService userService) {
        this.clientService = clientService;
        this.agencyService = agencyService;
        this.userService = userService;
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

    @PostMapping("/add/preferences")
    public ResponseEntity<Client> addPreferences(@RequestBody ClientPreferencesDTO clientPreferencesDTO,
                                                 @RequestHeader("Authorization") String jwtToken) {
        String email = JwtLoginDto.getEmailFromJwtToken(jwtToken);
        return new ResponseEntity<>(
                this.clientService.addPreferences(clientPreferencesDTO, email),
                HttpStatus.OK
        );
    }

    @GetMapping("/get/current")
    public ResponseEntity<? extends User> getCurrentUser(@RequestHeader("Authorization") String jwtToken) throws Throwable {
        String email = JwtLoginDto.getEmailFromJwtToken(jwtToken);
        return new ResponseEntity<>(
                this.userService.getUserWithEmail(email),
                HttpStatus.OK
        );
    }
}
