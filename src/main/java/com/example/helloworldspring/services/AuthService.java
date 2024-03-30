package com.example.helloworldspring.services;

import com.example.helloworldspring.commonTypes.UserRole;
import com.example.helloworldspring.dto.LoginDTO;
import com.example.helloworldspring.dto.LoginResponseDTO;
import com.example.helloworldspring.dto.RegisterDTO;
import com.example.helloworldspring.dto.RegisterResponseDTO;
import com.example.helloworldspring.entities.AuthEntity;
import com.example.helloworldspring.entities.User;
import com.example.helloworldspring.exceptionHandlers.CustomException;
import com.example.helloworldspring.exceptionHandlers.ExceptionCodes;
import com.example.helloworldspring.repositories.AuthRepository;
import com.example.helloworldspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthRepository authRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;
    @Autowired
    public AuthService(AuthRepository authRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.authRepository = authRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public RegisterResponseDTO register(RegisterDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        userRepository.save(user);

        AuthEntity authEntity = new AuthEntity();
        authEntity.setUsername(dto.getUsername());
        authEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
        authEntity.setUser(user);
        authEntity.setRole(dto.getRole());
        authRepository.save(authEntity);

        authRepository.save(authEntity);

        return new RegisterResponseDTO(authEntity.getUsername(), authEntity.getRole(), authEntity.getUser().getUserId());

    }
    public LoginResponseDTO login(LoginDTO dto) {

        AuthEntity authEntity = authRepository.findByUsername(dto.getUsername()).orElseThrow(() -> new
                CustomException(ExceptionCodes.INVALID_CREDENTIALS));
        if (!passwordEncoder.matches(dto.getPassword(), authEntity.getPassword())) {
            throw new CustomException(ExceptionCodes.INVALID_CREDENTIALS);
        }

        String token = jwtService.generateToken(authEntity);

        return new LoginResponseDTO(token);


    }
}
