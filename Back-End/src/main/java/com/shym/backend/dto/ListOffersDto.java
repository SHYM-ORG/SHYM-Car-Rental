package com.shym.backend.dto;

import com.shym.backend.enumeration.CarCategory;
import com.shym.backend.enumeration.CarModel;
import com.shym.backend.enumeration.Color;
import com.shym.backend.enumeration.FuelType;
import org.springframework.web.multipart.MultipartFile;

public record ListOffersDto (
        String offerId,
        int pricePerDay,
        boolean availableNow,
        CarModel model,
        String series,
        String image
){
}
