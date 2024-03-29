package com.example.helloworldspring.dto;

import com.example.helloworldspring.commonTypes.UserRole;

public class RegisterResponseDTO {
    private String username;
    private UserRole role;

    public RegisterResponseDTO(String username, UserRole role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRole getRole() {
        return role;
    }

}
