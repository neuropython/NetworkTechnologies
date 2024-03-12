package com.example.helloworldspring.controllers;

import com.example.helloworldspring.dto.UserDTO;
import com.example.helloworldspring.entities.User;
import com.example.helloworldspring.services.UserService;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody User addUser(@RequestBody UserDTO userDTO){
        return userService.addUser(userDTO);
    }



}
