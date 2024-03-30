package com.example.helloworldspring.dto;

import com.example.helloworldspring.commonTypes.UserRole;

public class RegisterResponseDTO {
    private String userId;
    private String username;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    private UserRole role;

    public RegisterResponseDTO(String username, UserRole role, Long userId) {
        this.username = username;
        this.role = role;
        this.userId = userId;
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
