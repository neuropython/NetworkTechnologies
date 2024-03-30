package com.example.helloworldspring.controllers;

import com.example.helloworldspring.dto.LoginDTO;
import com.example.helloworldspring.dto.LoginResponseDTO;
import com.example.helloworldspring.dto.RegisterDTO;
import com.example.helloworldspring.dto.RegisterResponseDTO;
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

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterDTO requestBody) {
        RegisterResponseDTO dto = authService.register(requestBody);
        return new ResponseEntity(dto, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<LoginResponseDTO> register(@RequestBody LoginDTO requestBody) {
        LoginResponseDTO dto = authService.login(requestBody);
        return new ResponseEntity(dto, HttpStatus.CREATED);
    }

//    private final AuthService authService;
//
//    @Autowired
//    public AuthController(AuthService authService) {
//        this.authService = authService;
//    }
//
//
//    @PostMapping("/register")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<RegisterResponseDto> register(@Valid @RequestBody RegisterDto requestBody, BindingResult bindingResult) {
//        CheckBindingExceptions.check(bindingResult);
//        RegisterResponseDto dto = authService.register(requestBody);
//        return new ResponseEntity<>(dto, HttpStatus.CREATED);
//    }

//    @PostMapping("/login")
//    @PreAuthorize("permitAll")
//    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginDto dto, BindingResult bindingResult) {
//        CheckBindingExceptions.check(bindingResult);
//        LoginResponseDto responseDto = authService.login(dto);
//        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
//    }

}
