package com.shym.backend.dto;

import com.shym.backend.enumeration.CarCategory;
import com.shym.backend.enumeration.CarModel;
import com.shym.backend.enumeration.Color;
import com.shym.backend.enumeration.FuelType;

public record DetailsOfferDto(
        String offerId,
        int pricePerDay,
        String description,
        boolean availableNow,
        CarModel model,
        CarCategory category,
        Color color,
        String series,
        FuelType fuelType,
        float consumptionRateCity,
        float consumptionRateHighway
){
}
