package com.shym.backend.service;

import com.shym.backend.dto.*;
import com.shym.backend.dtoMappers.OfferDtoMapper;
import com.shym.backend.exception.AgencyNotOwnerOfOfferException;
import com.shym.backend.exception.PriceRatioIncorrectException;
import com.shym.backend.exception.RentalOfferNotFoundException;
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

    public RentalOffer editOffer(EditOfferDTO dto, String jwtToken) {
        String email = JwtLoginDto.getEmailFromJwtToken(jwtToken);
        Agency agency = agencyService.getAgencyWithEmail(email);
        RentalOffer rentalOffer = rentalOfferRepository.findById(dto.id_offer()).orElseThrow(
                () -> new RentalOfferNotFoundException("Offer not found")
        );
        if (!rentalOffer.getAgency().getId().equals(agency.getId())) throw new AgencyNotOwnerOfOfferException("this offer is not owned by the agency!");
        rentalOffer.setAvailableNow(dto.availableNow());
        rentalOffer.setDescription(dto.description());
        rentalOffer.setPricePerDay(dto.pricePerDay());
        rentalOffer.getCar().setCategory(dto.category());
        rentalOffer.getCar().setColor(dto.color());
        rentalOffer.getCar().setConsumptionRateCity(dto.consumptionRateCity());
        rentalOffer.getCar().setModel(dto.model());
        rentalOffer.getCar().setSeries(dto.series());
        rentalOffer.getCar().setConsumptionRateHighway(dto.consumptionRateHighway());
        rentalOffer.getCar().setFuelType(dto.fuelType());
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
        RentalOffer rentalOffer = rentalOfferRepository.findById(offerId).orElseThrow(
                () -> new RentalOfferNotFoundException("Offer Not Found!")
        );
        if (!rentalOffer.getAgency().getId().equals(agency.getId())) throw new AgencyNotOwnerOfOfferException("this offer is not owned by the agency!");
        rentalOfferRepository.delete(rentalOffer);
    }

    public void changeState(ChangeStateOfferDTO dto, String jwtToken) {
        String email = JwtLoginDto.getEmailFromJwtToken(jwtToken);
        Agency agency = agencyService.getAgencyWithEmail(email);
        RentalOffer rentalOffer = rentalOfferRepository.findById(dto.id_offer()).orElseThrow(
                () -> new RentalOfferNotFoundException("Offer Not Found!")
        );
        if (!rentalOffer.getAgency().getId().equals(agency.getId())) throw new AgencyNotOwnerOfOfferException("this offer is not owned by the agency!");
        rentalOffer.setAvailableNow(dto.available_now());
        rentalOfferRepository.save(rentalOffer);
    }

    public void increasePrice(IncreasePrisePerCatDTO dto, String jwtToken) {
        String email = JwtLoginDto.getEmailFromJwtToken(jwtToken);
        Agency agency = agencyService.getAgencyWithEmail(email);
        if (dto.increaseRatio() > 100 || dto.increaseRatio() < 0) throw new PriceRatioIncorrectException(dto.increaseRatio() + "%");
        List<RentalOffer> offers = rentalOfferRepository.findRentalOfferByAgency(agency);
        for (RentalOffer offer: offers)
            if (offer.getCar().getCategory() == dto.category()) {
                offer.setPricePerDay((int)(offer.getPricePerDay() * (1 + ((double)dto.increaseRatio() / 100))));
                rentalOfferRepository.save(offer);
            }
    }

    public List<ListOffersDto> getAgencyOffers(String jwtToken) {
        String email = JwtLoginDto.getEmailFromJwtToken(jwtToken);
        Agency agency = agencyService.getAgencyWithEmail(email);
        List<ListOffersDto> rentalOffers = rentalOfferRepository.findRentalOfferByAgency(agency).stream().map(
                (RentalOffer offer) -> OfferDtoMapper.ListOfferDtoMapper(offer)
        ).toList();
        return rentalOffers;
    }

    public RentalOffer getOfferDetails(String id_offer, String jwtToken) {
        String email = JwtLoginDto.getEmailFromJwtToken(jwtToken);
        Agency agency = agencyService.getAgencyWithEmail(email);
        RentalOffer rentalOffer = rentalOfferRepository.findById(id_offer).orElseThrow(
                () -> new RentalOfferNotFoundException("Offer Not Found!")
        );
        if (!rentalOffer.getAgency().getId().equals(agency.getId())) throw new AgencyNotOwnerOfOfferException("this offer is not owned by the agency!");
        return rentalOffer;
    }
}
