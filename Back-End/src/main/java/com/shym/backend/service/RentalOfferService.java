package com.shym.backend.service;

import com.shym.backend.dto.AddOfferDTO;
import com.shym.backend.dto.JwtLoginDto;
import com.shym.backend.dtoMappers.OfferDtoMapper;
import com.shym.backend.model.Agency;
import com.shym.backend.model.RentalOffer;
import com.shym.backend.repository.RentalOfferRepository;
import org.springframework.stereotype.Service;

@Service
public class RentalOfferService {

    private RentalOfferRepository rentalOfferRepository;
    private AgencyService agencyService;
    private CarService carService;

    public RentalOfferService(RentalOfferRepository rentalOfferRepository,
                              AgencyService agencyService,
                              CarService carService) {
        this.rentalOfferRepository = rentalOfferRepository;
        this.agencyService = agencyService;
        this.carService = carService;
    }

    public RentalOffer addOffer(AddOfferDTO dto, String jwtToken) {
        String email = JwtLoginDto.getEmailFromJwtToken(jwtToken);
        Agency agency = agencyService.getAgencyWithEmail(email);
        RentalOffer rentalOffer = OfferDtoMapper.addOfferDtoMapper(dto);
        rentalOffer.setAgency(agency);
        rentalOffer.setCar(carService.addCar(rentalOffer.getCar()));
        return rentalOfferRepository.save(rentalOffer);
    }
}
