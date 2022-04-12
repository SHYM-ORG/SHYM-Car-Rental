package com.shym.backend.service;

import com.shym.backend.repository.RentalOfferRepository;
import org.springframework.stereotype.Service;

@Service
public class RentalOfferService {
    private RentalOfferRepository rentalOfferRepository;

    public RentalOfferService(RentalOfferRepository rentalOfferRepository) {
        this.rentalOfferRepository = rentalOfferRepository;
    }
}
