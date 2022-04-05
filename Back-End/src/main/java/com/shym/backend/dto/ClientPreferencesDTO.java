package com.shym.backend.dto;

import com.shym.backend.enumeration.CarCategory;
import com.shym.backend.enumeration.CarModel;

import java.util.List;

public record ClientPreferencesDTO (
        List<CarCategory> catPreferences,
        List<CarModel> modelPreferences
){
}
