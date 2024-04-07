package com.example.helloworldspring.dto;

import com.example.helloworldspring.commonTypes.UserRole;

public class UserDTO {
    private Long userId;
    private String username;
    private String password;
    private String email;
    private String role;
    private String name;


    public UserDTO(String username) {
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String firstName) {
        this.name = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return UserRole.valueOf(role);
    }

    public void setRole(String role) {
        this.role = role;
    }
}
