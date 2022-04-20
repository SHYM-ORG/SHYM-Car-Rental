package com.shym.backend.controller.controller;

import com.shym.backend.dto.*;
import com.shym.backend.model.Agency;
import com.shym.backend.model.Client;
import com.shym.backend.model.User;
import com.shym.backend.service.AgencyService;
import com.shym.backend.service.ClientService;
import com.shym.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PostMapping(path="/create/agency",
            consumes=MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Agency> createAgency(@RequestBody CreateAgencyDto createAgencyDto) {
        return new ResponseEntity<>(
                agencyService.createAgency(createAgencyDto),
                HttpStatus.OK
        );
    }

    @PutMapping(path="/edit/agency",
            consumes=MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Agency> editAgency(@ModelAttribute @Valid EditAgencyDto editAgencyDto,
                                             @RequestHeader("Authorization") String jwtToken) {
        String email = JwtLoginDto.getEmailFromJwtToken(jwtToken);
        return new ResponseEntity<>(
                agencyService.editAgency(editAgencyDto, email),
                HttpStatus.OK
        );
    }

    @PutMapping(path="/addImage/agency",
            consumes="multipart/form-data",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Agency> AddAgencyImage(@ModelAttribute @Valid AgencyImageDto agencyImageDto,
                                                 @RequestHeader("Authorization") String jwtToken) {
        String email = JwtLoginDto.getEmailFromJwtToken(jwtToken);
        return new ResponseEntity<>(
                agencyService.addAgencyImage(agencyImageDto.image(), email),
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

    @PostMapping("/add/location")
    public ResponseEntity<Client> addPreferences(@RequestHeader("Location") String location,
                                                 @RequestHeader("Authorization") String jwtToken) {
        String email = JwtLoginDto.getEmailFromJwtToken(jwtToken);
        this.agencyService.addLocation(location, email);
        return new ResponseEntity<>(
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
