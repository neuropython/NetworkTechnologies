package com.example.helloworldspring.dto;

public class LoginResponseDTO {
    private String token;

    private String Role;

    public String getRole() {
        return Role;
    }
    public LoginResponseDTO(String token, String Role) {
        this.token = token;
        this.Role = Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
