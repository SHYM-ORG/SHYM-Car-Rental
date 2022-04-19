package com.shym.backend.dto;

public record ChangeStateOfferDTO(
        String id_offer,
        boolean available_now
) {
}
