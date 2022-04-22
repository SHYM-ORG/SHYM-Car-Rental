package com.shym.backend.controller.controller;

import com.shym.backend.dto.*;
import com.shym.backend.model.RentalOffer;
import com.shym.backend.service.RentalOfferService;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping(path = "/api/offer")
public class RentalOfferController {

    private RentalOfferService rentalOfferService;

    public RentalOfferController(RentalOfferService rentalOfferService) {
        this.rentalOfferService = rentalOfferService;
    }

    @PostMapping(path = "/add",
            consumes="multipart/form-data",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentalOffer> addOffer(@ModelAttribute @Valid AddOfferDTO dto,
                                                @RequestHeader("Authorization") String jwtToken) {
        return new ResponseEntity<>(
                rentalOfferService.addOffer(dto, jwtToken),
                HttpStatus.OK
        );
    }

    @PutMapping(path = "/edit")
    public ResponseEntity<RentalOffer> editOffer(@RequestBody EditOfferDTO dto,
                                                 @RequestHeader("Authorization") String jwtToken) {
        return new ResponseEntity<>(
                rentalOfferService.editOffer(dto, jwtToken),
                HttpStatus.OK
        );
    }

    @PutMapping(path = "/changeState")
    public ResponseEntity<Void> changeStateOffer(@RequestBody ChangeStateOfferDTO dto,
                                                 @RequestHeader("Authorization") String jwtToken) {
        rentalOfferService.changeState(dto, jwtToken);
        return new ResponseEntity<>(
                HttpStatus.OK
        );
    }

    @PutMapping(path = "/increasePricePerCat")
    public ResponseEntity<Void> increaseOfferPricePerCat(@RequestBody IncreasePrisePerCatDTO dto,
                                                         @RequestHeader("Authorization") String jwtToken) {
        rentalOfferService.increasePrice(dto, jwtToken);
        return new ResponseEntity<>(
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
    public ResponseEntity<List<ListOffersDto>> deleteOffer(@RequestHeader("Authorization") String jwtToken) {
        return new ResponseEntity<> (
                rentalOfferService.getAgencyOffers(jwtToken),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/get/details")
    public ResponseEntity<RentalOffer> getOfferDetails(@RequestHeader("id_offer") String id_offer,
                                                       @RequestHeader("Authorization") String jwtToken) {
        return new ResponseEntity<> (
                rentalOfferService.getOfferDetails(id_offer, jwtToken),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/get/agencyAvailableCars")
    public ResponseEntity<List<ListOffersDto>> deleteAvailableCars(@RequestHeader("Authorization") String jwtToken) {
        return new ResponseEntity<> (
                rentalOfferService.getAgencyAvailableCars(jwtToken),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/get/agencyRentedCars")
    public ResponseEntity<List<ListOffersDto>> deleteRentedCars(@RequestHeader("Authorization") String jwtToken) {
        return new ResponseEntity<> (
                rentalOfferService.getAgencyRentedCars(jwtToken),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/get/offerImage/{imageName}",
                produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, "image/jpg"}
                /*produces = MediaType.IMAGE_PNG_VALUE*/)
    public ResponseEntity<?> getOfferImage(@PathVariable String imageName) throws IOException {
        /*InputStream in = getClass()
                .getResourceAsStream(System.getProperty("user.dir") + "data/files/" + imageName);
        return IOUtils.toByteArray(in);*/
        return new ResponseEntity<>(
                rentalOfferService.getImage(imageName),
                HttpStatus.OK
        );
    }
}
