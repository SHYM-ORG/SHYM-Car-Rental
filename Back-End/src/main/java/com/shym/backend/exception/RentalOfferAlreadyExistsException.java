package com.shym.backend.exception;

public class RentalOfferAlreadyExistsException extends RuntimeException{
    public RentalOfferAlreadyExistsException(String msg) {
        super(msg);
    }
}
