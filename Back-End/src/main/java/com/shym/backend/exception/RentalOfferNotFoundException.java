package com.shym.backend.exception;

public class RentalOfferNotFoundException extends RuntimeException{
    public RentalOfferNotFoundException(String msg) {
        super(msg);
    }
}
