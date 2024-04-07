package com.example.helloworldspring.dto;

public class BlacklistedTokenDTO {
    private String token;

    public BlacklistedTokenDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}