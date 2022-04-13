package com.shym.backend.dtoMappers;

import com.shym.backend.dto.AddOfferDTO;
import com.shym.backend.model.Car;
import com.shym.backend.model.RentalOffer;


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
}
