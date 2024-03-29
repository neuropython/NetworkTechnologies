package com.example.helloworldspring.controllers;

import com.example.helloworldspring.LoginForm;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@RestController
public class LoginController {

    @Value("${jwt.token}")
    private String key;
    // I have used code presented on the lecture
    // I do not own the rights to this code

    private final PasswordEncoder passwordEncoder;


    @Autowired
    public LoginController(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginForm loginForm){
        String encodedPassword = passwordEncoder.encode("password to encode");
        boolean ifTheSame = passwordEncoder.matches(loginForm.getPassword(), "encodedPasswordFormDB");
        //get user from database by login
        if(true){ //check password with password encoder
            long millis = System.currentTimeMillis();
            String token = Jwts.builder()
                    .issuedAt(new Date(millis))
                    .expiration(new Date(millis+5*60*100)) //5 minutes
                    .claim("id","userId") //insert user id
                    .claim("role","USER") //insert user role
                    .signWith(SignatureAlgorithm.HS256,key)
                    .compact();
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else { //password is incorrect or user doesn't exist
            return new ResponseEntity<>("Wrong login or password!", HttpStatus.UNAUTHORIZED);
        }
    }
}
