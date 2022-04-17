package com.shym.backend.dto;

import com.shym.backend.enumeration.CarCategory;
import com.shym.backend.enumeration.CarModel;
import com.shym.backend.enumeration.Color;
import com.shym.backend.enumeration.FuelType;
import org.springframework.web.multipart.MultipartFile;

public record AddOfferDTO(
    CarModel model,
    CarCategory category,
    Color color,
    String series,
    FuelType fuelType, //
    float consumptionRateCity,
    float consumptionRateHighway,
    int pricePerDay,
    String description,
    boolean availableNow,
    MultipartFile image
) {
}
