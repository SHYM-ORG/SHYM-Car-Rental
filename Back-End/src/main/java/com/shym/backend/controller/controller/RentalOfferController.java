package com.shym.backend.controller.controller;

import com.shym.backend.dto.AddOfferDTO;
import com.shym.backend.model.RentalOffer;
import com.shym.backend.service.RentalOfferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/offer")
public class RentalOfferController {

    private RentalOfferService rentalOfferService;

    public RentalOfferController(RentalOfferService rentalOfferService) {
        this.rentalOfferService = rentalOfferService;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<RentalOffer> addOffer(@RequestBody AddOfferDTO dto,
                                                @RequestHeader("Authorization") String jwtToken) {
        return new ResponseEntity<>(
                rentalOfferService.addOffer(dto, jwtToken),
                HttpStatus.OK
        );
    }
}
