package com.shym.backend.dto;

import com.shym.backend.enumeration.CarCategory;

public record IncreasePrisePerCatDTO(
        int increaseRatio, // in %
        CarCategory category
) {
}
