package com.shym.backend.dtoMappers;

import com.shym.backend.dto.AddOfferDTO;
import com.shym.backend.dto.ListOffersDto;
import com.shym.backend.model.Car;
import com.shym.backend.model.RentalOffer;
import com.shym.backend.utils.FileConfig;
import com.shym.backend.utils.FileUtils;
import org.springframework.web.multipart.MultipartFile;


public class OfferDtoMapper {

    public static RentalOffer addOfferDtoMapper(AddOfferDTO dto) {
        Car car = new Car(
                dto.model(), dto.category(), dto.color(),
                dto.series(), dto.fuelType(), dto.consumptionRateCity(),
                dto.consumptionRateHighway()
        );
        RentalOffer offer = new RentalOffer();
        offer.setCar(car);
        offer.setDescription(dto.description());
        offer.setPricePerDay(dto.pricePerDay());
        offer.setAvailableNow(dto.availableNow());
        return offer;
    }

    public static ListOffersDto ListOfferDtoMapper(RentalOffer offer) {
        ListOffersDto dto = new ListOffersDto(
                offer.getId(),
                offer.getPricePerDay(),
                offer.isAvailableNow(),
                offer.getCar().getModel(),
                offer.getCar().getSeries(),
                offer.getImagePath()
        );
        return dto;
    }
}
