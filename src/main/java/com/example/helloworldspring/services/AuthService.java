package com.example.helloworldspring.services;

import com.example.helloworldspring.commonTypes.UserRole;
import com.example.helloworldspring.dto.*;
import com.example.helloworldspring.entities.AuthEntity;
import com.example.helloworldspring.entities.BlacklistedTokens;
import com.example.helloworldspring.entities.User;
import com.example.helloworldspring.exceptionHandlers.CustomException;
import com.example.helloworldspring.exceptionHandlers.ExceptionCodes;
import com.example.helloworldspring.repositories.AuthRepository;
import com.example.helloworldspring.repositories.BlacklistedTokensRepository;
import com.example.helloworldspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    private final AuthRepository authRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    @Autowired
    private BlacklistedTokensRepository blacklistedTokensRepository;

    @Autowired
    public AuthService(AuthRepository authRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.authRepository = authRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public RegisterResponseDTO register(RegisterDTO dto) {
        try {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setRole(dto.getRole());


        userRepository.save(user);

        AuthEntity authEntity = new AuthEntity();
        authEntity.setUsername(dto.getUsername());
        authEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
        authEntity.setUser(user);
        authEntity.setRole(dto.getRole());
        authRepository.save(authEntity);

        authRepository.save(authEntity);

        return new RegisterResponseDTO(authEntity.getUsername(), authEntity.getRole(), authEntity.getUser().getUserId());
        } catch (Exception e) {
            throw new CustomException(ExceptionCodes.USER_ALREADY_EXISTS);
        }

    }
    public LoginResponseDTO login(LoginDTO dto) {

        AuthEntity authEntity = authRepository.findByUsername(dto.getUsername()).orElseThrow(() -> new
                CustomException(ExceptionCodes.INVALID_CREDENTIALS));
        if (!passwordEncoder.matches(dto.getPassword(), authEntity.getPassword())) {
            throw new CustomException(ExceptionCodes.INVALID_CREDENTIALS);
        }
        String role = authEntity.getRole().toString();

        String token = jwtService.generateToken(authEntity);

        return new LoginResponseDTO(token, role);
    }
    @Transactional
    public void logout(String token) {
        BlacklistedTokens blacklistedToken = new BlacklistedTokens();
        blacklistedToken.setToken(token);
        blacklistedTokensRepository.save(blacklistedToken);
    }

}
