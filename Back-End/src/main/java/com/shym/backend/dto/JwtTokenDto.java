package com.shym.backend.dto;

public class JwtTokenDto {

    private String Authorization;

    public JwtTokenDto(String Authorization) {
        this.Authorization = Authorization;
    }

    public String getAuthorization() {
        return Authorization;
    }

    public void setAuthorization(String Authorization) {
        this.Authorization = Authorization;
    }
}
