package com.mtt.secureaccessprocess.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse {

    @JsonProperty("jwt")
    private String jwt;

    public LoginResponse(String jwt) {
        this.jwt = jwt;
    }
}
