package com.shym.backend.exception;

public class AgencyNotOwnerOfOfferException extends RuntimeException{
    public AgencyNotOwnerOfOfferException(String msg) {
        super(msg);
    }
}
