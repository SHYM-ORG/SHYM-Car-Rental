package com.shym.backend.service;

import com.shym.backend.dto.AddOfferDTO;
import com.shym.backend.dto.JwtLoginDto;
import com.shym.backend.dtoMappers.OfferDtoMapper;
import com.shym.backend.exception.AgencyNotOwnerOfOfferException;
import com.shym.backend.exception.RentalOfferAlreadyExistsException;
import com.shym.backend.model.Agency;
import com.shym.backend.model.RentalOffer;
import com.shym.backend.repository.RentalOfferRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
@AllArgsConstructor
public class RentalOfferService {

    private RentalOfferRepository rentalOfferRepository;
    private AgencyService agencyService;
    private CarService carService;
    private FileService fileService;

    public RentalOffer addOffer(AddOfferDTO dto, String jwtToken) {
        String email = JwtLoginDto.getEmailFromJwtToken(jwtToken);
        Agency agency = agencyService.getAgencyWithEmail(email);
        RentalOffer rentalOffer = OfferDtoMapper.addOfferDtoMapper(dto);
        rentalOffer.setAgency(agency);
        rentalOffer.setCar(carService.addCar(rentalOffer.getCar()));
        try {
            fileService.uploadOfferImage(rentalOffer, dto.image());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return rentalOfferRepository.save(rentalOffer);
    }

    public void deleteOffer(String offerId, String jwtToken) {
        String email = JwtLoginDto.getEmailFromJwtToken(jwtToken);
        Agency agency = agencyService.getAgencyWithEmail(email);
        RentalOffer rentalOffer = rentalOfferRepository.findById(offerId).orElseThrow(() -> new RentalOfferAlreadyExistsException("Offer already exists!"));
        if (!rentalOffer.getAgency().getId().equals(agency.getId())) throw new AgencyNotOwnerOfOfferException("this offer is not owned by the agency!");
        rentalOfferRepository.delete(rentalOffer);
    }

    public List<RentalOffer> getAgencyOffers(String jwtToken) {
        String email = JwtLoginDto.getEmailFromJwtToken(jwtToken);
        Agency agency = agencyService.getAgencyWithEmail(email);
        List<RentalOffer> rentalOffers = rentalOfferRepository.findRentalOfferByAgency(agency);
        return rentalOffers;
    }
}
