package com.example.helloworldspring.dto;

import com.example.helloworldspring.commonTypes.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDTO {
    @JsonIgnore
    private Long userId;
    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private String role;
    private String name;


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

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return UserRole.valueOf(role).toString();
    }

    public void setRole(String role) {
        this.role = role;
    }
}
