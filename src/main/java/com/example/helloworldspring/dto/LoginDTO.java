package com.example.helloworldspring.dto;

public class LoginDTO {
    private String username;

    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private String password;

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
}
