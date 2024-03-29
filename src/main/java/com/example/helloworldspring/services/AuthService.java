package com.example.helloworldspring.services;

import com.example.helloworldspring.dto.LoginDTO;
import com.example.helloworldspring.dto.RegisterDTO;
import com.example.helloworldspring.dto.RegisterResponseDTO;
import com.example.helloworldspring.entities.AuthEntity;
import com.example.helloworldspring.entities.User;
import com.example.helloworldspring.exceptionHandlers.CustomException;
import com.example.helloworldspring.exceptionHandlers.ExceptionCodes;
import com.example.helloworldspring.repositories.AuthRepository;
import com.example.helloworldspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthRepository authRepository;
    private final UserRepository userRepository;
    @Autowired
    public AuthService(AuthRepository authRepository, UserRepository userRepository) {
        this.authRepository = authRepository;
        this.userRepository = userRepository;
    }

    public RegisterResponseDTO register(RegisterDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        User savedUser = userRepository.save(user);

        AuthEntity authEntity = new AuthEntity();
        authEntity.setUsername(dto.getUsername());
        authEntity.setPassword(dto.getPassword());
        authEntity.setUser(savedUser);
        authEntity.setRole(dto.getRole());
        authRepository.save(authEntity);

        AuthEntity savedAuthEntity = authRepository.save(authEntity);

        return new RegisterResponseDTO(savedAuthEntity.getUsername(), savedAuthEntity.getRole());

    }
    public void login(LoginDTO dto) {

        AuthEntity authEntity = authRepository.findByUsername(dto.getUsername()).orElseThrow(() -> new
                CustomException(ExceptionCodes.INVALID_CREDENTIALS));
        if (!authEntity.getPassword().equals(dto.getPassword())) {
            throw new CustomException(ExceptionCodes.INVALID_CREDENTIALS);
        }

    }
}
