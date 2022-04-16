package com.shym.backend.controller.controller;

import com.shym.backend.dto.AddOfferDTO;
import com.shym.backend.model.RentalOffer;
import com.shym.backend.service.RentalOfferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping(path = "/delete")
    public ResponseEntity<Void> deleteOffer(@RequestHeader("offer_id") String offer_id,
                                            @RequestHeader("Authorization") String jwtToken) {
        rentalOfferService.deleteOffer(offer_id, jwtToken);
        return new ResponseEntity<> (
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/get/agencyOffer")
    public ResponseEntity<List<RentalOffer>> deleteOffer(@RequestHeader("Authorization") String jwtToken) {
        return new ResponseEntity<> (
                rentalOfferService.getAgencyOffers(jwtToken),
                HttpStatus.OK
        );
    }
}
