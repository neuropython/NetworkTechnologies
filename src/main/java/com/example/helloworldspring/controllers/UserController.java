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
@CrossOrigin(origins = "http://localhost:3000")

public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    /**
     * Get me - get the current user
     * @return
     */
    @GetMapping("/me")
    public ResponseEntity getMe(Principal principal) {
        String username = principal.getName();
        Long id = userService.getUser(username).getId();
        return ResponseEntity.ok(id);
    }
    /**
     * Get all users
     * @return
     */
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    /**
     * Delete me - delete the current user
     */
    @DeleteMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity deleteUser(Principal principal) {
        String username = principal.getName();
        userService.deleteUser(username);
        return ResponseEntity.ok().build();
    }

    /**
     * Update me - update the current user
     * @return
     */
    @PatchMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity updateUser(Principal principal, @RequestBody UserDTO userDTO) {
        String username = principal.getName();
        return ResponseEntity.ok(userService.patchUser(username, userDTO));
    }
    /**
     * Delete user by username
     * @param username
     * @return
     */
    @DeleteMapping("/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.ok().build();
    }
}
