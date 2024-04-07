package com.example.helloworldspring.controllers;

import com.example.helloworldspring.dto.*;
import com.example.helloworldspring.entities.BlacklistedTokens;
import com.example.helloworldspring.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    private final AuthService authService;

    /**
     * Register - only admin can register new users
     * @return
     */
    @PostMapping("/register")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterDTO requestBody) {
        RegisterResponseDTO dto = authService.register(requestBody);
        return new ResponseEntity(dto, HttpStatus.CREATED);
    }

    /**
     * Login - everyone can log in
     * @return
     */
    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO requestBody) {
        LoginResponseDTO dto = authService.login(requestBody);
        return new ResponseEntity(dto, HttpStatus.CREATED);
    }
    /**
     * Logout - currently logout method doesn't work because
     * of not properly saving the token in the database
     * @return
     */

    @PostMapping("/logout")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity logout(@RequestBody BlacklistedTokenDTO requestBody) {
        System.out.println(requestBody.getToken());
        authService.logout(requestBody.getToken());
        return new ResponseEntity(HttpStatus.OK);
    }

}
