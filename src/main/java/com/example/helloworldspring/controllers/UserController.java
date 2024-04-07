package com.example.helloworldspring.controllers;

import com.example.helloworldspring.dto.UserDTO;
import com.example.helloworldspring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/users")
@PreAuthorize("isAuthenticated()")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity getMe(Principal principal) {
        String username = principal.getName();
        return ResponseEntity.ok(userService.getUser(username));
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @DeleteMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity deleteUser(Principal principal) {
        String username = principal.getName();
        userService.deleteUser(username);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity updateUser(Principal principal, @RequestBody UserDTO userDTO) {
        String username = principal.getName();
        return ResponseEntity.ok(userService.patchUser(username, userDTO));
    }
//
//    @DeleteMapping("/{username}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity deleteUser(@PathVariable String username) {
//        userService.deleteUser(username);
//        return ResponseEntity.ok().build();
//    }
}
